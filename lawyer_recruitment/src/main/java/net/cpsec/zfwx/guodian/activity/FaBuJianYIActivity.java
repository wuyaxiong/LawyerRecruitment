package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.Map;

public class  FaBuJianYIActivity extends BaseActivity implements View.OnClickListener {
private TextView tv_back,tv_complete;
    private EditText et_jianyi;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu_jian_yi);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid= sp.getString("uid","");
        initView();
    }

    private void initView() {
        tv_back= (TextView) findViewById(R.id.tv_jianyi_back);
        tv_back.setOnClickListener(this);
        tv_complete= (TextView) findViewById(R.id.tv_jianyi_complete);
        tv_complete.setOnClickListener(this);
        et_jianyi= (EditText) findViewById(R.id.et_jianyi_zhengwen);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_jianyi_back:
                finish();
                break;
            case  R.id.tv_jianyi_complete:
                if (TextUtils.isEmpty(et_jianyi.getText().toString().trim())) {
                    android.widget.Toast.makeText(FaBuJianYIActivity.this,"请输入建议内容！", android.widget.Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    RequestMap params = new RequestMap();
                    params.put("uid", uid);
                    params.put("content", et_jianyi.getText().toString());
                    setParams(NetUrl.QINGNIAN_ZHISHENG_JIANYI, params, 0);
                }
                break;
        }
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                Toast.prompt(this, "发布失败，稍后重试");
                return;
            } else {
                Toast.prompt(this, "发布建议成功");
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }
}
