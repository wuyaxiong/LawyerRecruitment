package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import net.cpsec.zfwx.guodian.adapter.TongZhiPingLunAdapter;
import net.cpsec.zfwx.guodian.entity.ZhengCeTongzhiDetailBean;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhengCeTongZhiDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_title, tv_name, tv_time, tv_content;
    private ImageView iv_back,iv_shoucang,iv_yishoucang;
    private FrameLayout fl_shoucang;
    private NoScrollListView lv_images, lv_pinglun;
    private TongZhiPingLunAdapter pinlunAdapter;
    private View view;
    ListImageAdapter adapter;
    private ImageView ivRightToolBar;
    String aid;
    String images;
    String uid;
    private ZhengCeTongzhiDetailBean pinglunBean;
    private EditText et_pinglun;
    private Button btn;
    final ArrayList imageUrls = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_zheng_ce_tong_zhi_detail);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        Intent intent = getIntent();
        aid = intent.getExtras().getString("aid");
        initView();
    }

    private void initView() {
        view=findViewById(R.id.view2);
        et_pinglun = (EditText) findViewById(R.id.et_tongzhi_pinglun);
        btn = (Button) findViewById(R.id.btn_tongzhi_pinglun);
        ivRightToolBar = (ImageView) findViewById(R.id.ivRightToolBar);
        btn.setOnClickListener(this);
        iv_back = (ImageView) findViewById(R.id.toolbar_nav_iv);
        tv_title = (TextView) findViewById(R.id.tv_tongzhi_title);
        tv_name = (TextView) findViewById(R.id.tv_tongzhi_username);
        tv_time = (TextView) findViewById(R.id.tv_tongzhi_time);
        tv_content = (TextView) findViewById(R.id.tv_tongzhi_content);
        lv_images = (NoScrollListView) findViewById(R.id.lv_tongzhi_tupian);
        lv_pinglun = (NoScrollListView) findViewById(R.id.lv_tongzhi_pinglun);
        iv_back.setOnClickListener(this);
        initData();
        fl_shoucang= (FrameLayout) findViewById(R.id.fl_shoucang);
        iv_yishoucang = (ImageView) findViewById(R.id.iv_tiezi_yishoucang);
        iv_shoucang = (ImageView) findViewById(R.id.iv_tiezi_shoucang);
        fl_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_yishoucang.getVisibility()==View.VISIBLE){
                    Toast.prompt(ZhengCeTongZhiDetailActivity.this, "已收藏，不能重复收藏");
//                    RequestMap params = new RequestMap();
//                    params.put("notice_id", aid);
//                    params.put("uid", uid);
//                    setParams(NetUrl.QINCHUN_ZHENGCETONGZHI_SHOUCANG, params, 3);
                }else {
                    RequestMap params = new RequestMap();
                    params.put("notice_id", aid);
                    params.put("uid", uid);
                    setParams(NetUrl.QINCHUN_ZHENGCETONGZHI_SHOUCANG, params, 2);
                }
            }
        });

    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("uid", uid + "");
        params.put("aid", aid + "");
        setParams(NetUrl.QINCHUN_ZHENGCETONGZHI_DETAIL, params, 0);
    }

    private void setAdapter() {

    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId) {
            case 0:
                try {
                    pinglunBean = JSON.parseObject(response, ZhengCeTongzhiDetailBean.class);
                    tv_title.setText(pinglunBean.getInfor().getNotice_info().getTitle());
                    tv_name.setText(pinglunBean.getInfor().getNotice_info().getCname());
                    tv_time.setText(DateUtil.converTime(pinglunBean.getInfor().getNotice_info().getTime()));
                    tv_content.setText(pinglunBean.getInfor().getNotice_info().getComment());
                    int is_collection=pinglunBean.getInfor().getIs_collection();
                    if (is_collection==0){
                        iv_shoucang.setVisibility(View.VISIBLE);
                        iv_yishoucang.setVisibility(View.GONE);
                    }else {
                        iv_shoucang.setVisibility(View.GONE);
                        iv_yishoucang.setVisibility(View.VISIBLE);
                    }
                    images = pinglunBean.getInfor().getNotice_info().getImage();
                    imageUrls.clear();
                    if (images == null || images.isEmpty()) {
                        lv_images.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                    } else {
                        String[] tupians = images.split(",");
                        for (String substr : tupians) {
                            imageUrls.add("http://" + substr);
                            lv_images.setVisibility(View.VISIBLE);
                            view.setVisibility(View.VISIBLE);
                        }
                    }
                    PicCheck(lv_images);
                    adapter = new ListImageAdapter(this, imageUrls);
                    lv_images.setAdapter(adapter);
                    List<ZhengCeTongzhiDetailBean.InforBean.NoticeCommentBean> coment_info = pinglunBean.getInfor().getNotice_comment();
                    pinlunAdapter = new TongZhiPingLunAdapter(this, coment_info);
                    lv_pinglun.setAdapter(pinlunAdapter);
                    final UMWeb web = new UMWeb("http://bbs.91huiban.com/public/share3.html?id ="+aid);
                    web.setTitle(pinglunBean.getInfor().getNotice_info().getTitle());//标题
                    web.setThumb(new UMEmoji(this,R.mipmap.ic_launcher));  //缩略图
                    web.setDescription(pinglunBean.getInfor().getNotice_info().getComment());//描述
                    ivRightToolBar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new ShareAction(ZhengCeTongZhiDetailActivity.this) .withMedia(web)
                                    .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                                    .setCallback(new UMShareListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA share_media) {
                                        }
                                        @Override
                                        public void onResult(SHARE_MEDIA share_media) {
                                            Toast.prompt(ZhengCeTongZhiDetailActivity.this,share_media+"分享成功");
                                        }
                                        @Override
                                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                            Toast.prompt(ZhengCeTongZhiDetailActivity.this,share_media+"分享失败"+throwable.getMessage());
                                            if(throwable!=null){
                                                Log.d("throw","throw:"+throwable.getMessage());
                                            }
                                        }
                                        @Override
                                        public void onCancel(SHARE_MEDIA share_media) {
                                            Toast.prompt(ZhengCeTongZhiDetailActivity.this,share_media+"分享取消");
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
                        Toast.prompt(ZhengCeTongZhiDetailActivity.this, "评论失败，请稍后重试");
                        return;
                    } else {
                        initData();
                        et_pinglun.setText("");
                        Toast.prompt(ZhengCeTongZhiDetailActivity.this, "评论成功");
                    }
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 2:
                if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                    Toast.prompt(this, "收藏失败，稍后重试！");
                } else {
                    Toast.prompt(this, "收藏成功!");
                    iv_yishoucang.setVisibility(View.VISIBLE);
                    iv_shoucang.setVisibility(View.GONE);
                }
                break;
//            case 4:
//                if (!"200".equals(JSON.parseObject(response).getString("code"))) {
//                    Toast.prompt(this, "取消收藏失败，稍后重试!");
//                } else {
//                    Toast.prompt(this, "已取消收藏");
//                    iv_yishoucang.setVisibility(View.GONE);
//                    iv_shoucang.setVisibility(View.VISIBLE);
//                }
//                break;
        }

    }
    public void PicCheck(ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ZhengCeTongZhiDetailActivity.this,PicCheckActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("images", images);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.toolbar_nav_iv:
                finish();
                break;
            case R.id.btn_tongzhi_pinglun:
                if (et_pinglun.getText().toString().trim().isEmpty()) {
                    Toast.prompt(this, "评论内容不能为空");
                    return;
                } else {
                    RequestMap params1 = new RequestMap();
                    params1.put("aid", aid + "");
                    params1.put("uid", uid);
                    params1.put("comment", et_pinglun.getText().toString());
                    setParams(NetUrl.QINCHUN_ZHENGCETONGZHI_PINGLUN, params1, 1);
                }
                break;
        }
    }
}
