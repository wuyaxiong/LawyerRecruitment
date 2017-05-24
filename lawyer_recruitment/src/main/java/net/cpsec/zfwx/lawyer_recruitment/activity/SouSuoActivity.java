package net.cpsec.zfwx.lawyer_recruitment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.cpsec.zfwx.lawyer_recruitment.R;

public class SouSuoActivity extends BaseActivity {
private TextView tv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        initView();
    }

    private void initView() {
        tv_back= (TextView) findViewById(R.id.tv_sousuo_back);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        finish();
    }
}
