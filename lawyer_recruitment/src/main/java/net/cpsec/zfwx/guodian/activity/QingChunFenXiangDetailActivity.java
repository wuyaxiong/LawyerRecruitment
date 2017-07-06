package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMWeb;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.ListImageAdapter;
import net.cpsec.zfwx.guodian.adapter.PingLunAdapter;
import net.cpsec.zfwx.guodian.entity.AnLiMeiWenBean;
import net.cpsec.zfwx.guodian.entity.Comment_info;
import net.cpsec.zfwx.guodian.entity.Goods_article_all;
import net.cpsec.zfwx.guodian.entity.MeiWenPingLunBean;
import net.cpsec.zfwx.guodian.entity.MeiWenPingLunInfor;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QingChunFenXiangDetailActivity extends BaseActivity implements View.OnClickListener, YRecycleview.OnRefreshAndLoadMoreListener {
    private TextView tv_title, tv_name, tv_time, tv_content;
    private ImageView iv_back;
    private YRecycleview yrv_list;
    private PingLunAdapter adapter;
    String gid,images;
    private List<Comment_info> coment_list;
    private MeiWenPingLunBean pinglunBean;
    private AnLiMeiWenBean anLiMeiWenBean;
    private Goods_article_all good_artical;
    private MeiWenPingLunInfor pinglunInfor;
    private boolean isRefreshState = true;//是否刷新
    private EditText et_pinglun;
    private ImageView ivRightToolBar;
    private Button btn;
    String uid;
    private NoScrollListView list_image;
    //初始化（模拟）数据
    final ArrayList imageUrls = new ArrayList<String>();
    ListImageAdapter imageadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_qing_chun_fen_xiang_detail);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid= sp.getString("uid","");
        initView();
    }

    private void initView() {
        et_pinglun= (EditText) findViewById(R.id.et_meiwen_pinglun);
        ivRightToolBar = (ImageView) findViewById(R.id.ivRightToolBar);
        btn= (Button) findViewById(R.id.btn_meiwen_pinglun);
        btn.setOnClickListener(this);
        list_image = (NoScrollListView) findViewById(R.id.lv_fengxiang_images);
        iv_back = (ImageView) findViewById(R.id.toolbar_nav_iv);
        tv_title = (TextView) findViewById(R.id.tv_meiwendetail_title);
        tv_name = (TextView) findViewById(R.id.tv_meiwendetail_username);
        tv_time = (TextView) findViewById(R.id.tv_meiwendetail_time);
        tv_content = (TextView) findViewById(R.id.tv_meiwendetail_content);
        yrv_list = (YRecycleview) findViewById(R.id.yrv_pinglun_list);
        yrv_list.setRefreshAndLoadMoreListener(this);
        iv_back.setOnClickListener(this);
        Intent intent = getIntent();
        gid = intent.getExtras().getString("gid");
        initData();
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("gid", gid + "");
        setParams(NetUrl.QINCHUN_MEIWEN_DETAIL, params, 0);
    }

    private void setAdapter() {
        if (isRefreshState && null != coment_list) {
            adapter = new PingLunAdapter(this, coment_list);
            yrv_list.setLayoutManager(new LinearLayoutManager(this));
            yrv_list.setNestedScrollingEnabled(false);
            yrv_list.setAdapter(adapter);
        }else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId){
            case 0:
                try {
                    pinglunBean = JSON.parseObject(response, MeiWenPingLunBean.class);
                    Debugging.debugging("position      =      " + (null == pinglunBean));
                    coment_list = pinglunBean.getInfor().getComment_info();
                    good_artical = pinglunBean.getInfor().getGoods_article_all();
                    tv_title.setText(good_artical.getTitle());
                    tv_name.setText(good_artical.getUsername());
                    tv_time.setText(DateUtil.converTime(good_artical.getTime()));
                    tv_content.setText(good_artical.getContent());
                   images = pinglunBean.getInfor().getGoods_article_all().getImage();
                    if (images == null|| images.isEmpty() ) {
                        list_image.setVisibility(View.GONE);
                    } else {
                        String[] tupians = images.split(",");
                        //每次刷新前清空图片列表
                        imageUrls.clear();
                        for (String substr : tupians) {
                            imageUrls.add("http://" + substr);
                            list_image.setVisibility(View.VISIBLE);
                        }
                    }

                    PicCheck(list_image);
                    //初始化适配器
                    imageadapter= new ListImageAdapter(this, imageUrls);
                    list_image.setAdapter(imageadapter);
                    final UMWeb web = new UMWeb("http://bbs.91huiban.com/public/share.html?id = "+gid+"&staus = 0");
                    web.setTitle(good_artical.getTitle());//标题
                    web.setThumb(new UMEmoji(this,R.mipmap.ic_launcher));  //缩略图
                    web.setDescription(good_artical.getContent());//描述
                    ivRightToolBar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new ShareAction(QingChunFenXiangDetailActivity.this) .withMedia(web)
                                    .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                                    .setCallback(new UMShareListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA share_media) {
                                        }
                                        @Override
                                        public void onResult(SHARE_MEDIA share_media) {
                                            Toast.prompt(QingChunFenXiangDetailActivity.this,share_media+"分享成功");
                                        }
                                        @Override
                                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                            Toast.prompt(QingChunFenXiangDetailActivity.this,share_media+"分享失败"+throwable.getMessage());
                                            if(throwable!=null){
                                                Log.d("throw","throw:"+throwable.getMessage());
                                            }
                                        }
                                        @Override
                                        public void onCancel(SHARE_MEDIA share_media) {
                                            Toast.prompt(QingChunFenXiangDetailActivity.this,share_media+"分享取消");
                                        }
                                    }).open();
                        }
                    });
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                setAdapter();
                break;
            case 1:
                try {
                    if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                        Toast.prompt(this, JSON.parseObject(response).getString("infor"));
                        return;
                    } else {
                        initData();
                        et_pinglun.setText("");
                        android.widget.Toast.makeText(this,"评论成功", android.widget.Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
                    }
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_nav_iv:
                finish();
                break;
            case R.id.btn_meiwen_pinglun:
                if (et_pinglun.getText().toString().trim().isEmpty()||et_pinglun.getText().toString().trim() == null){
                    Toast.prompt(this, "评论内容不能为空！");
                    return;
                } else {
                    RequestMap params = new RequestMap();
                    params.put("gid", gid + "");
                    params.put("uid", uid);
                    params.put("status", 1 + "");
                    params.put("comment", et_pinglun.getText().toString());
                    setParams(NetUrl.QINGCHUN_MEIWEN_PINGLUN, params, 1);
                }
                break;
        }
    }
    public void PicCheck(ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(QingChunFenXiangDetailActivity.this,PicCheckActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("images", images);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onRefresh() {
        isRefreshState = true;
        yrv_list.setReFreshComplete();
        initData();
    }

    @Override
    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yrv_list.setNoMoreData(true);
    }
}
