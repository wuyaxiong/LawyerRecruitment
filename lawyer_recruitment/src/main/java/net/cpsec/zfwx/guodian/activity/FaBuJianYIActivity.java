package net.cpsec.zfwx.guodian.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;

public class FaBuJianYIActivity extends Activity implements View.OnClickListener {
private TextView tv_back,tv_complete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_jian_yi);
        initView();
    }

    private void initView() {
        tv_back= (TextView) findViewById(R.id.tv_jianyi_back);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_jianyi_back:
                finish();
                break;
        }
    }
}
