package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.MyCenterBean;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.Map;


public class MyCenterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back, iv_bianjiziliao;
    private RelativeLayout rl_fatie, rl_toupiao, rl_fankui, rl_shoucang, rl_wenda,rl_out;
    private RoundedImageView riv_head;
    private TextView tv_name, tv_sex, tv_birth, tv_ins, tv_mianmao, tv_cname, tv_address;
    private MyCenterBean myCenterBean;
String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_center);
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
//        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
////        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid= sp.getString("uid","");
        initView();
        initDatas();
    }

    private void initDatas() {
        RequestMap params = new RequestMap();
        params.put("uid",uid);
        setParams(NetUrl.MY_CENTER, params, 0);
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            if ("200".equals(JSON.parseObject(response).getString("code")))
            myCenterBean = JSON.parseObject(response, MyCenterBean.class);
            tv_name.setText(myCenterBean.getInfor().getUsername());
            tv_cname.setText(myCenterBean.getInfor().getCname());
            tv_birth.setText(myCenterBean.getInfor().getBirth());
            tv_mianmao.setText(myCenterBean.getInfor().getBackground());
            tv_address.setText(myCenterBean.getInfor().getAddress());
            tv_ins.setText(myCenterBean.getInfor().getIntroduction());
            int s = myCenterBean.getInfor().getSex();
            if (s == 0) {
                tv_sex.setText("男");
            } else {
                tv_sex.setText("女");
            }
            ImageLoader.getInstance().displayImage("http://" + myCenterBean.getInfor().getUserpic(), riv_head);
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_mycenter_back);
        rl_fatie = (RelativeLayout) findViewById(R.id.rl_mycenter_fatie);
        iv_bianjiziliao = (ImageView) findViewById(R.id.iv_myventer_bianjiziliao);
        rl_toupiao = (RelativeLayout) findViewById(R.id.rl_mycenter_toupiao);
        rl_shoucang = (RelativeLayout) findViewById(R.id.rl_mycenter_shoucang);
        rl_wenda = (RelativeLayout) findViewById(R.id.rl_mycenter_wenda);
        rl_wenda.setOnClickListener(this);
        rl_shoucang.setOnClickListener(this);
        rl_toupiao.setOnClickListener(this);
        iv_bianjiziliao.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        rl_fatie.setOnClickListener(this);
        riv_head = (RoundedImageView) findViewById(R.id.mCenter_riv_header);
        tv_name = (TextView) findViewById(R.id.mCenter_tv_myName);
        tv_sex = (TextView) findViewById(R.id.mCenter_tv_sex);
        tv_birth = (TextView) findViewById(R.id.mCenter_tv_birth);
        tv_ins = (TextView) findViewById(R.id.mCenter_tv_instruction);
        tv_mianmao = (TextView) findViewById(R.id.mCenter_tv_mianmao);
        tv_cname = (TextView) findViewById(R.id.mCenter_tv_cname);
        tv_address = (TextView) findViewById(R.id.mCenter_tv_address);
        rl_out= (RelativeLayout) findViewById(R.id.rl_login_out);
        rl_out.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //返回箭头
            case R.id.iv_mycenter_back:
                finish();
                break;
            //发帖页面
            case R.id.rl_mycenter_fatie:
                startActivity(new Intent(MyCenterActivity.this, MyTieZiActivity.class));
                break;
            //编辑资料
            case R.id.iv_myventer_bianjiziliao:
                Intent intent = new Intent(MyCenterActivity.this, XiuGaiXinXiActivity.class);
                intent.putExtra("username", myCenterBean.getInfor().getUsername());
                intent.putExtra("sex", myCenterBean.getInfor().getSex());
                intent.putExtra("ins", myCenterBean.getInfor().getIntroduction());
                intent.putExtra("birth", myCenterBean.getInfor().getBirth());
                intent.putExtra("mianmao", myCenterBean.getInfor().getBackground());
                intent.putExtra("danwei", myCenterBean.getInfor().getCname());
                intent.putExtra("address", myCenterBean.getInfor().getAddress());
                intent.putExtra("pic", myCenterBean.getInfor().getUserpic());
                startActivity(intent);
                break;
            //投票页面
            case R.id.rl_mycenter_toupiao:
                startActivity(new Intent(MyCenterActivity.this, MyTouPiaoActivity.class));
                break;
//            //反馈页面
//            case R.id.rl_mycenter_fankui:
//                startActivity(new Intent(MyCenterActivity.this, FanKuiActivity.class));
//                break;
            //收藏页面
            case R.id.rl_mycenter_shoucang:
                startActivity(new Intent(MyCenterActivity.this, ShouCangActivity.class));
                break;
            //问答页面
            case R.id.rl_mycenter_wenda:
                startActivity(new Intent(MyCenterActivity.this, WenDaActivity.class));
                break;
            case R.id.rl_login_out:
                SharedPreferences sp1 = getSharedPreferences("isfirst", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor1 = sp1.edit();
                editor1.putString("isfirst", "0");
                editor1.commit();
                startActivity(new Intent(MyCenterActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 8 && resultCode == 8) {
            Intent intent = getIntent();
            tv_name.setText(data.getStringExtra("name"));
            tv_birth.setText(data.getStringExtra("birth"));
        }
    }
}
