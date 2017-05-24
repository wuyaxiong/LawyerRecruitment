package net.cpsec.zfwx.lawyer_recruitment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import net.cpsec.zfwx.lawyer_recruitment.R;

public class XinShengTiWenActivity extends BaseActivity {
    private TextView tv_back,tv_complete;
    private EditText et_zhuti,et_zhengwen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xin_sheng_ti_wen);
        initView();
    }

    private void initView() {
        tv_back= (TextView) findViewById(R.id.tv_tiwen_back);
        et_zhuti= (EditText) findViewById(R.id.et_tiwen_zhuti);
        et_zhengwen= (EditText) findViewById(R.id.et_tiwen_zhengwen);
        tv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_tiwen_back:
                finish();
                break;
        }
    }
}
