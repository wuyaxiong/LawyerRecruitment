package net.cpsec.zfwx.guodian.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;

public class TouPiaoXiangQingActivity extends BaseActivity {
    private TextView tv_toupiao_title,tv_context;
    private RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_piao_xiang_qi);

        initView();
    }

    private void initView() {
        Bundle bundle = getIntent().getExtras();
        String time = bundle.getString("time");
        String title = bundle.getString("title");
        String description = bundle.getString("description");
        int voter_id = bundle.getInt("voter_id");

        tv_toupiao_title= (TextView) findViewById(R.id.tv_toupiao_title);
        tv_context = (TextView) findViewById(R.id.tv_context);
        tv_toupiao_title.setText(title);
        tv_context.setText(description);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        radioButton4 = (RadioButton) findViewById(R.id.radioButton4);
        img_back = (ImageView) findViewById(R.id.img_back);
        img_back.setOnClickListener(this);

        switch (voter_id){
            case 1:
                radioButton1.setChecked(true);
                break;
            case 2:
                radioButton2.setChecked(true);
                break;
            case 3:
                radioButton3.setChecked(true);
                break;
            case 4:
                radioButton4.setChecked(true);
                break;
            default:
                break;
        }

    }

    //箭头返回
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                finish();
                break;
        }
    }
}
