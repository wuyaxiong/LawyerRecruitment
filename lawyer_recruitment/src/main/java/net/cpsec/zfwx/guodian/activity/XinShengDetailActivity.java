package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMWeb;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.ListImageAdapter;
import net.cpsec.zfwx.guodian.entity.XinShengDetailBean;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.view.NoScrollListView;

import java.util.ArrayList;
import java.util.Map;

public class XinShengDetailActivity extends BaseActivity {
    private ImageView iv_back;
    private RoundedImageView head;
    private TextView tv_name, tv_time, tv_content, tv_huifutime, tv_huifu, tv_prise;
    String uid, ask_id, images;
    private XinShengDetailBean detail;
    private NoScrollListView listview;
    private ImageView ivRightToolBar;
    ListImageAdapter adapter;
    //初始化（模拟）数据
    final ArrayList imageUrls = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_sheng_detail);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        ask_id = intent.getStringExtra("ask_id");
        Debugging.debugging("ask_id" + ask_id);
        ivRightToolBar = (ImageView) findViewById(R.id.ivRightToolBar);
        listview = (NoScrollListView) findViewById(R.id.list_xinshengdetail_tupian);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        head = (RoundedImageView) findViewById(R.id.riv_xinshengdetail_head);
        tv_name = (TextView) findViewById(R.id.tv_xinshengdetail_name);
        tv_content = (TextView) findViewById(R.id.tv_xinshengdetail_content);
        tv_time = (TextView) findViewById(R.id.tv_xinshengdetail_time);
        tv_huifutime = (TextView) findViewById(R.id.tv_xinshengdetail_huifutime);
        tv_huifu = (TextView) findViewById(R.id.tv_xinshengdetail_huifucontent);
        tv_prise = (TextView) findViewById(R.id.tv_xinshengdetail_dianzan);
        initData();
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("ask_id", ask_id);
        setParams(NetUrl.XINSHENG_DETAIL, params, 1);
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
          try {
        detail = JSON.parseObject(response, XinShengDetailBean.class);
        String userpic = detail.getInfor().getUserpic();
        String username = detail.getInfor().getUsername();
        String content = detail.getInfor().getContent();
        String huifu = detail.getInfor().getComment();
        long asktime = detail.getInfor().getAsktime();
        long huifutime = detail.getInfor().getTime();
        int prise_num = detail.getInfor().getPraise();
        ImageLoader.getInstance().displayImage("http://" + userpic, head);
        tv_name.setText(username);
        tv_content.setText(content);
        tv_time.setText(DateUtil.converTime(asktime));
        tv_huifutime.setText(DateUtil.converTime(huifutime));
        tv_huifu.setText(huifu);
        tv_prise.setText(prise_num+"");
        images = detail.getInfor().getImage().toString();
        if (images.isEmpty() || images == null) {
            listview.setVisibility(View.GONE);
        } else {
            String[] tupians = images.split(",");
            //每次刷新前清空图片列表
            imageUrls.clear();
            for (String substr : tupians) {
                imageUrls.add("http://" + substr);
                listview.setVisibility(View.VISIBLE);
            }
        }

        PicCheck(listview);
        //初始化适配器
        adapter = new ListImageAdapter(this, imageUrls);
        adapter.notifyDataSetChanged();
        listview.setAdapter(adapter);
              final UMWeb web = new UMWeb("http://bbs.91huiban.com/public/share1.html?id ="+ask_id);
              web.setTitle(username);//标题
              web.setThumb(new UMEmoji(this,R.mipmap.ic_launcher));  //缩略图
              web.setDescription(content);//描述
              ivRightToolBar.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      new ShareAction(XinShengDetailActivity.this) .withMedia(web)
                              .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                              .setCallback(new UMShareListener() {
                                  @Override
                                  public void onStart(SHARE_MEDIA share_media) {
                                  }
                                  @Override
                                  public void onResult(SHARE_MEDIA share_media) {
                                      Toast.prompt(XinShengDetailActivity.this,share_media+"分享成功");
                                  }
                                  @Override
                                  public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                      Toast.prompt(XinShengDetailActivity.this,share_media+"分享失败"+throwable.getMessage());
                                      if(throwable!=null){
                                          Log.d("throw","throw:"+throwable.getMessage());
                                      }
                                  }
                                  @Override
                                  public void onCancel(SHARE_MEDIA share_media) {
                                      Toast.prompt(XinShengDetailActivity.this,share_media+"分享取消");
                                  }
                              }).open();
                  }
              });
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }

    public void PicCheck(ListView listView){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(XinShengDetailActivity.this,PicCheckActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("images", images);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
