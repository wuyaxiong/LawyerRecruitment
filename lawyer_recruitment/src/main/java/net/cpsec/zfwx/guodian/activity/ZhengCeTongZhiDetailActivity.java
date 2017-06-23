package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.TongZhiPingLunAdapter;
import net.cpsec.zfwx.guodian.adapter.WenTiXiangQingAdapter;
import net.cpsec.zfwx.guodian.entity.ZhengCeTongzhiDetailBean;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.view.NoScrollListView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ZhengCeTongZhiDetailActivity extends BaseActivity implements View.OnClickListener {
    private TextView tv_title, tv_name, tv_time, tv_content;
    private ImageView iv_back;
    private NoScrollListView lv_images, lv_pinglun;
    private TongZhiPingLunAdapter pinlunAdapter;
    WenTiXiangQingAdapter adapter;
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
        et_pinglun = (EditText) findViewById(R.id.et_tongzhi_pinglun);
        btn = (Button) findViewById(R.id.btn_tongzhi_pinglun);
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
    }

    private void initData() {
        RequestMap params = new RequestMap();
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
                    Debugging.debugging("position      =      " + (null == pinglunBean));
                    tv_title.setText(pinglunBean.getInfor().getNotice_info().getTitle());
                    tv_name.setText(pinglunBean.getInfor().getNotice_info().getCname());
                    tv_time.setText(DateUtil.converTime(pinglunBean.getInfor().getNotice_info().getTime()));
                    tv_content.setText(pinglunBean.getInfor().getNotice_info().getComment());
                    images = pinglunBean.getInfor().getNotice_info().getImage();
                    imageUrls.clear();
                    if (images == null || images.isEmpty()) {
                        lv_images.setVisibility(View.GONE);
                    } else {
                        String[] tupians = images.split(",");
                        for (String substr : tupians) {
                            imageUrls.add("http://" + substr);
                            lv_images.setVisibility(View.VISIBLE);
                        }
                    }
                    adapter = new WenTiXiangQingAdapter(this, imageUrls);
                    lv_images.setAdapter(adapter);
                    List<ZhengCeTongzhiDetailBean.InforBean.NoticeCommentBean> coment_info = pinglunBean.getInfor().getNotice_comment();
                    pinlunAdapter = new TongZhiPingLunAdapter(this, coment_info);
                    lv_pinglun.setAdapter(pinlunAdapter);
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
        }

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
