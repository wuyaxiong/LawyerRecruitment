package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.JianYiDetailBean;
import net.cpsec.zfwx.guodian.utils.DateUtil;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.Map;

public class JianYiDetailActivity extends BaseActivity {
    private ImageView iv_back;
    private RoundedImageView head;
    private TextView tv_name, tv_time, tv_content, tv_huifutime, tv_huifu, tv_prise;
    String uid, advice_id;
    private JianYiDetailBean detail;
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
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("advice_id", advice_id);
        setParams(NetUrl.JIANYI_DETAIL, params, 1);
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId){
            case 1:
                try {
                    detail = JSON.parseObject(response, JianYiDetailBean.class);
                    String userpic = (String) detail.getInfor().get(0).getUserpic();
                    String username = detail.getInfor().get(0).getUsername();
                    String content = detail.getInfor().get(0).getContent();
                    String huifu = detail.getInfor().get(0).getComment();
                    long asktime = detail.getInfor().get(0).getAsktime();
                    long huifutime = detail.getInfor().get(0).getTime();
                    int prise_num = detail.getInfor().get(0).getPraise();
                    ImageLoader.getInstance().displayImage("http://" + userpic, head);
                    tv_name.setText(username);
                    tv_content.setText(content);
                    tv_time.setText(DateUtil.converTime(asktime));
                    tv_huifutime.setText(DateUtil.converTime(huifutime));
                    tv_huifu.setText(huifu);
                    tv_prise.setText(prise_num+"");

                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
        }
    }
}
