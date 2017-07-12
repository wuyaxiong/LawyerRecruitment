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
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMEmoji;
import com.umeng.socialize.media.UMWeb;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.TieZiPIngLunAdapter;
import net.cpsec.zfwx.guodian.adapter.WenTiXiangQingAdapter;
import net.cpsec.zfwx.guodian.entity.DianZanBean;
import net.cpsec.zfwx.guodian.entity.TieZiComment_info;
import net.cpsec.zfwx.guodian.entity.TieZiDetailBean;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TieZiDetailActivity extends BaseActivity {
    private ImageView iv_back, iv_shoucang, iv_dainzan, iv_yishoucang, iv_quxiaodainzan;
    private RoundedImageView head;
    private ImageView ivRightToolBar;
    private View view;
    private TextView tv_name, tv_time, tv_content, tv_title, tv_label, tv_dainzan, tv_pinglun,tv_jubao;
    private NoScrollListView list_image, lv_pinglun;
    String artical_id;
    private TieZiDetailBean tieZiDetailBean;
    String images;
    private EditText et_pinglun;
    private Button btn_pinglun;
    WenTiXiangQingAdapter adapter;
    TieZiPIngLunAdapter pinlunAdapter;
    //初始化（模拟）数据
    final ArrayList imageUrls = new ArrayList<String>();
    List<TieZiComment_info> coment_info;
    String uid;
    private FrameLayout fl_shoucang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tie_zi_detail);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initView();
        initData();
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("uid", uid);
        params.put("article_id", artical_id);
        setParams(NetUrl.TIEZI_DETAIL, params, 0);
    }

    private void initView() {
        view = findViewById(R.id.view2);
        Intent intent = getIntent();
        artical_id = intent.getStringExtra("artical_id");
        fl_shoucang = (FrameLayout) findViewById(R.id.fl_shoucang);
        iv_yishoucang = (ImageView) findViewById(R.id.iv_tiezi_yishoucang);
        iv_shoucang = (ImageView) findViewById(R.id.iv_tiezi_shoucang);
        tv_jubao= (TextView) findViewById(R.id.tv_tiezixiangqing_jubao);
        tv_jubao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestMap params = new RequestMap();
                params.put("uid", uid);
                params.put("aid", artical_id);
                setParams(NetUrl.TIEZI_JUBAO, params, 6);
            }
        });
        fl_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iv_yishoucang.getVisibility() == View.VISIBLE) {
                    RequestMap params = new RequestMap();
                    params.put("aid", artical_id);
                    params.put("uid", uid);
                    setParams(NetUrl.TIEZI_QUXIAOSHOUCANG, params, 4);
                } else {
                    RequestMap params = new RequestMap();
                    params.put("aid", artical_id);
                    params.put("uid", uid);
                    setParams(NetUrl.TIEZI_SHOUCANG, params, 2);
                }
            }
        });
//        iv_shoucang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RequestMap params = new RequestMap();
//                params.put("aid", artical_id);
//                params.put("uid", uid);
//                setParams(NetUrl.TIEZI_SHOUCANG, params, 2);
//            }
//        });
        iv_quxiaodainzan = (ImageView) findViewById(R.id.iv_quxiao_dianzan);
        iv_quxiaodainzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestMap params = new RequestMap();
                params.put("aid", artical_id);
                params.put("uid", uid);
                setParams(NetUrl.TIEZI_DIANZAN, params, 5);
            }
        });
        iv_dainzan = (ImageView) findViewById(R.id.iv_tiezi_dainzan);
        iv_dainzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestMap params = new RequestMap();
                params.put("aid", artical_id);
                params.put("uid", uid);
                setParams(NetUrl.TIEZI_DIANZAN, params, 3);
            }
        });
        iv_back = (ImageView) findViewById(R.id.toolbar_nav_iv);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        head = (RoundedImageView) findViewById(R.id.riv_tiezidetail_head);
        tv_name = (TextView) findViewById(R.id.tv_tiezixiangqing_name);
        tv_title = (TextView) findViewById(R.id.tv_tiezixiangqing_title);
        tv_time = (TextView) findViewById(R.id.tv_tiezixiangqing_time);
        tv_content = (TextView) findViewById(R.id.tv_tiezixiangqing_content);
        list_image = (NoScrollListView) findViewById(R.id.list_tizi_tupian);
        lv_pinglun = (NoScrollListView) findViewById(R.id.lv_tiezi_pinglun);
        et_pinglun = (EditText) findViewById(R.id.et_tiezi_pinglun);
        btn_pinglun = (Button) findViewById(R.id.btn_tiezi_pinglun);
        tv_dainzan = (TextView) findViewById(R.id.tv_tiezi_dianzan);
        tv_pinglun = (TextView) findViewById(R.id.tv_tiezi_huifu);
        et_pinglun = (EditText) findViewById(R.id.et_tiezi_pinglun);
        btn_pinglun = (Button) findViewById(R.id.btn_tiezi_pinglun);
        btn_pinglun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_pinglun.getText().toString().trim() == null || et_pinglun.getText().toString().isEmpty()) {
                    android.widget.Toast.makeText(TieZiDetailActivity.this, "评论内容不能为空！", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    RequestMap params = new RequestMap();
                    params.put("aid", artical_id);
                    params.put("uid", uid);
                    params.put("content", et_pinglun.getText().toString());
                    setParams(NetUrl.TIEZI_PINGLUN, params, 1);
                }
            }
        });
        ivRightToolBar = (ImageView) findViewById(R.id.ivRightToolBar);
//        final UMWeb web = new UMWeb("http://bbs.91huiban.com/public/share.html?id = "+artical_id+"&staus = 0");
//        web.setTitle(title);//标题
//        web.setThumb(new UMEmoji(this,R.mipmap.ic_launcher));  //缩略图
//        web.setDescription(content);//描述
//        ivRightToolBar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                new ShareAction(TieZiDetailActivity.this) .withMedia(web)
//                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN)
//                        .setCallback(new UMShareListener() {
//                            @Override
//                            public void onStart(SHARE_MEDIA share_media) {
//
//                            }
//
//                            @Override
//                            public void onResult(SHARE_MEDIA share_media) {
//                                Toast.prompt(TieZiDetailActivity.this,share_media+"分享成功");
//
//                            }
//
//                            @Override
//                            public void onError(SHARE_MEDIA share_media, Throwable throwable) {
//                                Toast.prompt(TieZiDetailActivity.this,share_media+"分享失败"+throwable.getMessage());
//                                if(throwable!=null){
//                                    Log.d("throw","throw:"+throwable.getMessage());
//                                }
//                            }
//
//                            @Override
//                            public void onCancel(SHARE_MEDIA share_media) {
//                                Toast.prompt(TieZiDetailActivity.this,share_media+"分享取消");
//                            }
//                        }).open();
//            }
//        });
        initData();
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId) {
            case 0:
                try {
                    tieZiDetailBean = JSON.parseObject(response, TieZiDetailBean.class);
                    String userpic = tieZiDetailBean.getInfor().getForum_info().getUserpic();
                    String username = tieZiDetailBean.getInfor().getForum_info().getUsername();
                    String title = tieZiDetailBean.getInfor().getForum_info().getTitle();
                    String content = tieZiDetailBean.getInfor().getForum_info().getContent();
                    // String label=tieZiDetailBean.getInfor().getForum_info().get
                    // String images = tieZiDetailBean.getInfor().getForum_info().getImage();
                    int prise_num = tieZiDetailBean.getInfor().getPrise_num();
                    int coment_num = tieZiDetailBean.getInfor().getComment_num();
                    ImageLoader.getInstance().displayImage("http://" + userpic, head);
                    int is_praise = tieZiDetailBean.getInfor().getIs_praise();
                    if (is_praise == 0) {
                        iv_dainzan.setVisibility(View.VISIBLE);
                        iv_quxiaodainzan.setVisibility(View.GONE);
                    } else {
                        iv_dainzan.setVisibility(View.GONE);
                        iv_quxiaodainzan.setVisibility(View.VISIBLE);
                    }
                    int iscollect = tieZiDetailBean.getInfor().getIs_collection();
                    if (iscollect == 0) {
                        iv_shoucang.setVisibility(View.VISIBLE);
                        iv_yishoucang.setVisibility(View.GONE);
                    } else {
                        iv_shoucang.setVisibility(View.GONE);
                        iv_yishoucang.setVisibility(View.VISIBLE);
                    }
                    int time = tieZiDetailBean.getInfor().getForum_info().getTime();
                    tv_title.setText(title);
                    tv_name.setText(username);
                    tv_content.setText(content);
                    tv_time.setText(DateUtil.converTime(time));
                    tv_dainzan.setText(prise_num + "");
                    tv_pinglun.setText(coment_num + "");
                    images = tieZiDetailBean.getInfor().getForum_info().getImage().toString();
                    if (images.isEmpty() || images == null) {
                        list_image.setVisibility(View.GONE);
                        view.setVisibility(View.GONE);
                    } else {
                        String[] tupians = images.split(",");
                        //每次刷新前清空图片列表
                        imageUrls.clear();
                        for (String substr : tupians) {
                            imageUrls.add("http://" + substr);
                            list_image.setVisibility(View.VISIBLE);
                            view.setVisibility(View.VISIBLE);
                        }
                    }
                    PicCheck(list_image);
                    //初始化适配器
                    adapter = new WenTiXiangQingAdapter(this, imageUrls);
                    coment_info = tieZiDetailBean.getInfor().getComment_info();
                    pinlunAdapter = new TieZiPIngLunAdapter(this, coment_info);
                    adapter.notifyDataSetChanged();
                    pinlunAdapter.notifyDataSetChanged();
                    list_image.setAdapter(adapter);
                    lv_pinglun.setAdapter(pinlunAdapter);
                    final UMWeb web = new UMWeb("http://bbs.91huiban.com/public/share.html?id = " + artical_id + "&staus = 0");
                    web.setTitle(title);//标题
                    web.setThumb(new UMEmoji(this, R.mipmap.ic_launcher));  //缩略图
                    web.setDescription(content);//描述
                    ivRightToolBar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            new ShareAction(TieZiDetailActivity.this).withMedia(web)
                                    .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                                    .setCallback(new UMShareListener() {
                                        @Override
                                        public void onStart(SHARE_MEDIA share_media) {
                                        }

                                        @Override
                                        public void onResult(SHARE_MEDIA share_media) {
                                            Toast.prompt(TieZiDetailActivity.this, share_media + "分享成功");
                                        }

                                        @Override
                                        public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                                            Toast.prompt(TieZiDetailActivity.this, share_media + "分享失败" + throwable.getMessage());
                                            if (throwable != null) {
                                                Log.d("throw", "throw:" + throwable.getMessage());
                                            }
                                        }

                                        @Override
                                        public void onCancel(SHARE_MEDIA share_media) {
                                            Toast.prompt(TieZiDetailActivity.this, share_media + "分享取消");
                                        }
                                    }).open();
                        }
                    });
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 1:
                if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                    Toast.prompt(this, "评论失败，稍后重试！");
                } else {
                    initData();
                    pinlunAdapter.notifyDataSetChanged();
                    adapter.notifyDataSetChanged();
                    Toast.prompt(this, "评论成功！");
                    coment_info.clear();
                    et_pinglun.setText("");
                }
                break;
            case 2:
                if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                    Toast.prompt(this, "收藏失败，稍后重试！");
                } else {
                    // if ("0".equals(JSON.parseObject(response).getString("infor"))) {
                    // initData();
                    Toast.prompt(this, "收藏成功！");
                    iv_yishoucang.setVisibility(View.VISIBLE);
                    iv_shoucang.setVisibility(View.GONE);
                    // iv_shoucang.setImageResource(R.drawable.icon_collect);
//                    } else {
//                        Toast.prompt(this, "帖子已收藏");
//                        iv_yishoucang.setVisibility(View.VISIBLE);
//                        iv_shoucang.setVisibility(View.GONE);
//                      //  iv_shoucang.setImageResource(R.drawable.icon_collect);
//                    }
                }
                break;
            case 3:
                try {
                    DianZanBean dianZanBean = JSON.parseObject(response, DianZanBean.class);
                    if (200 != dianZanBean.getCode()) {
                        Toast.prompt(this, "点赞失败，稍后重试！");
                    } else {
                        initData();
                        Toast.prompt(this, "点赞成功！");
                    }
//                    else if (0 == dianZanBean.getInfor().getRes()) {
//                        initData();
//                        Toast.prompt(this, "点赞成功！");
//                    } else if (1 == dianZanBean.getInfor().getRes()) {
//                        initData();
//                        Toast.prompt(this, "取消点赞成功！");
//                    }
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 4:
                if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                    Toast.prompt(this, "取消收藏失败，稍后重试！");
                } else {
                    Toast.prompt(this, "已取消收藏");
                    iv_yishoucang.setVisibility(View.GONE);
                    iv_shoucang.setVisibility(View.VISIBLE);
//                    if ("0".equals(JSON.parseObject(response).getString("infor"))) {
//                        initData();
//                        Toast.prompt(this, "收藏成功！");
//                        iv_yishoucang.setVisibility(View.VISIBLE);
//                        iv_shoucang.setVisibility(View.GONE);
//                        // iv_shoucang.setImageResource(R.drawable.icon_collect);
//                    } else {
//                        Toast.prompt(this, "帖子已收藏");
//                        iv_yishoucang.setVisibility(View.VISIBLE);
//                        iv_shoucang.setVisibility(View.GONE);
//                        //  iv_shoucang.setImageResource(R.drawable.icon_collect);
//                    }
                }
                break;
            case 5:
                try {
                    DianZanBean dianZanBean = JSON.parseObject(response, DianZanBean.class);
                    if (200 != dianZanBean.getCode()) {
                        Toast.prompt(this, "取消点赞失败，稍后重试！");
                    } else {
                        initData();
                        Toast.prompt(this, "取消点赞成功！");
                    }
//                    else if (0 == dianZanBean.getInfor().getRes()) {
//                        initData();
//                        Toast.prompt(this, "点赞成功！");
//                    } else if (1 == dianZanBean.getInfor().getRes()) {
//                        initData();
//                        Toast.prompt(this, "取消点赞成功！");
//                    }
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 6:
                if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                    Toast.prompt(this, "收藏失败，稍后重试！");
                } else {
                    Toast.prompt(this, "举报成功!");
                }
                break;
        }
    }

    public void PicCheck(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TieZiDetailActivity.this, PicCheckActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("images", images);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
