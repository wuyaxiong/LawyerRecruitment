package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import net.cpsec.zfwx.guodian.entity.JianYiDetailBean;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.Map;

public class JianYiDetailActivity extends BaseActivity {
    private ImageView iv_back,iv_dainzan, iv_quxiaodainzan;
    private RoundedImageView head;
    private TextView tv_name, tv_time, tv_content, tv_huifutime, tv_huifu, tv_prise;
    String uid, advice_id;
    private JianYiDetailBean detail;
    private ImageView ivRightToolBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jian_yi_detail);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        advice_id = intent.getStringExtra("advice_id");
        ivRightToolBar = (ImageView) findViewById(R.id.ivRightToolBar);
        Debugging.debugging("ask_id" + advice_id);
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
        iv_quxiaodainzan= (ImageView) findViewById(R.id.iv_quxiao_dianzan);
        iv_quxiaodainzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.prompt(JianYiDetailActivity.this,"已经点赞，不能重复点赞");
//                RequestMap params = new RequestMap();
//                params.put("notice_id", ask_id);
//                params.put("uid", uid);
//                setParams(NetUrl.TIEZI_DIANZAN, params, 3);
            }
        });
        iv_dainzan = (ImageView) findViewById(R.id.iv_tiezi_dainzan);
        iv_dainzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestMap params = new RequestMap();
                params.put("aid", advice_id);
                params.put("uid", uid);
                setParams(NetUrl.JIANYI_DETAIL_DIANZAN, params, 2);
            }
        });

    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("advice_id", advice_id);
        params.put("uid", uid);
        setParams(NetUrl.JIANYI_DETAIL, params, 1);
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId) {
            case 1:
                try {
                    detail = JSON.parseObject(response, JianYiDetailBean.class);
                    String userpic = (String) detail.getInfor().getAdvice_detail().get(0).getUserpic();
                    String username = detail.getInfor().getAdvice_detail().get(0).getUsername();
                    String content = detail.getInfor().getAdvice_detail().get(0).getContent();
                    String huifu = detail.getInfor().getAdvice_detail().get(0).getComment();
                    long asktime = detail.getInfor().getAdvice_detail().get(0).getAsktime();
                    long huifutime = detail.getInfor().getAdvice_detail().get(0).getTime();
                    int prise_num = detail.getInfor().getAdvice_detail().get(0).getPraise();
                    int is_praise = detail.getInfor().getIs_praise();
                    Debugging.debugging("praise=================="+is_praise);
                    if (is_praise == 0) {
                        iv_dainzan.setVisibility(View.VISIBLE);
                        iv_quxiaodainzan.setVisibility(View.GONE);
                    } else {
                        iv_dainzan.setVisibility(View.GONE);
                        iv_quxiaodainzan.setVisibility(View.VISIBLE);
                    }
                    ImageLoader.getInstance().displayImage("http://" + userpic, head);
                    tv_name.setText(username);
                    tv_content.setText(content);
                    tv_time.setText(DateUtil.converTime(asktime));
                    tv_huifutime.setText(DateUtil.converTime(huifutime));
                    tv_huifu.setText(huifu);
                    tv_prise.setText(prise_num + "");
                    final UMWeb web = new UMWeb("http://bbs.91huiban.com/public/share2.html?id ="+advice_id);
                    web.setTitle(username);//标题
                    web.setThumb(new UMEmoji(this,R.mipmap.ic_launcher));  //缩略图
                    web.setDescription(content);//描述
                    ivRightToolBar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new ShareAction(JianYiDetailActivity.this) .withMedia(web)
                                    .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
                                    .setCallback(new UMShareListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA share_media) {
                                        }
                                        @Override
                                        public void onResult(SHARE_MEDIA share_media) {
                                            Toast.prompt(JianYiDetailActivity.this,share_media+"分享成功");
                                        }
                                        @Override
                                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                            Toast.prompt(JianYiDetailActivity.this,share_media+"分享失败"+throwable.getMessage());
                                            if(throwable!=null){
                                                Log.d("throw","throw:"+throwable.getMessage());
                                            }
                                        }
                                        @Override
                                        public void onCancel(SHARE_MEDIA share_media) {
                                            Toast.prompt(JianYiDetailActivity.this,share_media+"分享取消");
                                        }
                                    }).open();
                        }
                    });
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 2:
                if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                    Toast.prompt(this, "点赞失败，稍后重试!");
                } else {
                    Toast.prompt(this, "点赞成功!");
                    initData();
                }
                break;
        }
    }
}
