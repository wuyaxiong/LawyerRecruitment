package net.cpsec.zfwx.guodian.activity;

import android.content.Intent;
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
import net.cpsec.zfwx.guodian.entity.MyCenterInfor;
import net.cpsec.zfwx.guodian.utils.NetUrl;

import java.util.Map;


public class MyCenterActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back, iv_bianjiziliao;
    private RelativeLayout rl_fatie, rl_toupiao, rl_fankui, rl_shoucang, rl_wenda;
    private RoundedImageView riv_head;
    private TextView tv_name, tv_sex, tv_birth, tv_ins, tv_mianmao, tv_cname, tv_address;
    private MyCenterBean myCenterBean;
    private MyCenterInfor myCenterInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_center);
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();
        initDatas();
    }

    private void initDatas() {
        RequestMap params = new RequestMap();
        params.put("uid", 3 + "");
        setParams(NetUrl.MY_CENTER, params, 0);
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        myCenterBean = JSON.parseObject(response, MyCenterBean.class);
        myCenterInfor = myCenterBean.getInfor();
        tv_name.setText(myCenterInfor.getUsername());
        tv_cname.setText(myCenterInfor.getCname());
        tv_birth.setText(myCenterInfor.getBirth());
        tv_mianmao.setText(myCenterInfor.getBackground());
        tv_address.setText(myCenterInfor.getAddress());
        tv_ins.setText(myCenterInfor.getIntroduction());
        int s = myCenterInfor.getSex();
        if (s == 0) {
            tv_sex.setText("男");
        } else {
            tv_sex.setText("女");
        }
        ImageLoader.getInstance().displayImage("http://"+myCenterInfor.getUserpic(),riv_head);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_mycenter_back:
                finish();
                break;
            case R.id.rl_mycenter_fatie:
                startActivity(new Intent(MyCenterActivity.this, MyTieZiActivity.class));
                break;
            case R.id.iv_myventer_bianjiziliao:
                Intent intent = new Intent(MyCenterActivity.this, XiuGaiXinXiActivity.class);
                intent.putExtra("username",myCenterInfor.getUsername());
                intent.putExtra("sex",myCenterInfor.getSex());
                intent.putExtra("ins",myCenterInfor.getIntroduction());
                intent.putExtra("birth",myCenterInfor.getBirth());
                intent.putExtra("mianmao",myCenterInfor.getBackground());
                intent.putExtra("danwei",myCenterInfor.getCname());
                intent.putExtra("address",myCenterInfor.getAddress());
                intent.putExtra("pic",myCenterInfor.getUserpic());
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
            case R.id.rl_mycenter_wenda:
                startActivity(new Intent(MyCenterActivity.this, WenDaActivity.class));
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
