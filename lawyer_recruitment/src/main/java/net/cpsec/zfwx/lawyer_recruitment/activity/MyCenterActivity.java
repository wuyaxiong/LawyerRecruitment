package net.cpsec.zfwx.lawyer_recruitment.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import net.cpsec.zfwx.lawyer_recruitment.R;


public class MyCenterActivity extends Activity implements View.OnClickListener {
private ImageView iv_back,iv_bianjiziliao;
    private RelativeLayout rl_fatie,rl_toupiao,rl_fankui,rl_shoucang,rl_wenda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_center);
        initView();
    }

    private void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_mycenter_back);
        rl_fatie= (RelativeLayout) findViewById(R.id.rl_mycenter_fatie);
        iv_bianjiziliao= (ImageView) findViewById(R.id.iv_myventer_bianjiziliao);
        rl_toupiao= (RelativeLayout) findViewById(R.id.rl_mycenter_toupiao);
        rl_fankui= (RelativeLayout) findViewById(R.id.rl_mycenter_fankui);
        rl_shoucang= (RelativeLayout) findViewById(R.id.rl_mycenter_shoucang);
        rl_wenda= (RelativeLayout) findViewById(R.id.rl_mycenter_wenda);
        rl_wenda.setOnClickListener(this);
        rl_shoucang.setOnClickListener(this);
        rl_fankui.setOnClickListener(this);
        rl_toupiao.setOnClickListener(this);
        iv_bianjiziliao.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        rl_fatie.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_mycenter_back:
                finish();
                break;
            case R.id.rl_mycenter_fatie:
                startActivity(new Intent(MyCenterActivity.this,MyTieZiActivity.class));
                break;
            case R.id.iv_myventer_bianjiziliao:
                startActivity(new Intent(MyCenterActivity.this,XiuGaiXinXiActivity.class));
                break;
            case R.id.rl_mycenter_toupiao:
                startActivity(new Intent(MyCenterActivity.this,MyTouPiaoActivity.class));
                break;
            case R.id.rl_mycenter_fankui:
                startActivity(new Intent(MyCenterActivity.this,FanKuiActivity.class));
                break;
            case R.id.rl_mycenter_shoucang:
                startActivity(new Intent(MyCenterActivity.this,ShouCangActivity.class));
                break;
            case R.id.rl_mycenter_wenda:
                startActivity(new Intent(MyCenterActivity.this,WenDaActivity.class));
                break;
        }
    }

}
