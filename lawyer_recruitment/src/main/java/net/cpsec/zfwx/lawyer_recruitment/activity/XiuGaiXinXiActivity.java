package net.cpsec.zfwx.lawyer_recruitment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;
import com.makeramen.roundedimageview.RoundedImageView;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.utils.NetUrl;
import net.cpsec.zfwx.lawyer_recruitment.utils.Toast;

import java.util.Map;


public class XiuGaiXinXiActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_back;
    private RoundedImageView riv_header;
    private EditText etName, etJianjie, etBirth, etMianmao, etDanwei, etaAddress;
    TextView tv_complete;
    RadioButton rbt_man, rbt_wuman;
    int sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiu_gai_xin_xi);
        initView();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        tv_complete = (TextView) findViewById(R.id.tv_xinxi_complete);
        tv_complete.setOnClickListener(this);
        riv_header = (RoundedImageView) findViewById(R.id.riv_header);
        rbt_man = (RadioButton) findViewById(R.id.rbt_man);
        rbt_wuman = (RadioButton) findViewById(R.id.rbt_woman);
        if (rbt_man.isChecked()) {
            sex = 0;
        } else if (rbt_wuman.isChecked()) {
            sex = 1;
        }
        etName = (EditText) findViewById(R.id.et_xinxi_name);
        etJianjie = (EditText) findViewById(R.id.et_xinxi_jianjie);
        etBirth = (EditText) findViewById(R.id.et_birth);
        etMianmao = (EditText) findViewById(R.id.et_xinxi_mianmao);
        etDanwei = (EditText) findViewById(R.id.et_xinxi_danwei);
        etaAddress = (EditText) findViewById(R.id.et_xinxi_address);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_fatie_complete:
                RequestMap params = new RequestMap();
                params.put("username", etName.getText().toString());
                params.put("introduction", etJianjie.getText().toString());
                params.put("sex", sex+"");
                params.put("birth",etBirth.getText().toString());
                params.put("background",etMianmao.getText().toString());
                params.put("cid",etaAddress.getText().toString());
                setParams(NetUrl.XIUGAI_XINXI, params, 0);
                break;
        }
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            if (!"成功".equals(JSON.parseObject(response).getString("msg"))) {
                Toast.prompt(this, JSON.parseObject(response).getString("infor"));
                return;
            } else {
                Intent intent = new Intent(this, MyCenterActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }
}
