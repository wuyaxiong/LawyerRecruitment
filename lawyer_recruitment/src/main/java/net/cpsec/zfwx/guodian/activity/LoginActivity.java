package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.utils.Constant;
import net.cpsec.zfwx.guodian.utils.DingShiQiUtil;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.utils.VerifyPhoneNumber;

import java.util.Map;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_login;
    private EditText etPhoneNumber, etVerificationCode;
    private String phoneNums, code;
    private Button imageButton;
//   // 登录请求 在成功的回调中将返回的token数据用SharedPreferences将token持久化 并 跳转到登录成功的界面
//    SharedPreferences sp = getSharedPreferences(PreferencesStorageKey.UID, Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = sp.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        imageButton = (Button) findViewById(R.id.ib_login_button);
        imageButton.setOnClickListener(this);
        btn_login = (Button) findViewById(R.id.btn_login);
        etPhoneNumber = (EditText) findViewById(R.id.et_login_phonenumber);
        etVerificationCode = (EditText) findViewById(R.id.et_login_phonecode);
        btn_login.setOnClickListener(this);
//        etPhoneNumber.setText("17600382402");
//        etVerificationCode.setText("1234");
    }

    @Override
    public void onClick(View v) {
        phoneNums = etPhoneNumber.getText().toString().trim();
        code = etVerificationCode.getText().toString().trim();
        switch (v.getId()) {
            case R.id.ib_login_button:
                if (etPhoneNumber.getText().toString().trim().isEmpty()){
                    Toast.prompt(this, "请输入手机号");
                    phoneNums = "";
                    return;
                }else if (!judgePhoneNums(phoneNums)){
                    phoneNums = "";
                    return;
                }else{
                    DingShiQiUtil.init(imageButton);
                    DingShiQiUtil.open();
                    RequestMap params = new RequestMap();
                    params.put("phone", phoneNums);
                    setParams(NetUrl.REGIST, params, 0);
                }
                break;
            case R.id.btn_login:
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                phoneNums = etPhoneNumber.getText().toString().trim();
                // 1. 通过规则判断手机号
                if (!judgePhoneNums(phoneNums)) {
                    phoneNums = "";
                    return;
                }
                if (etVerificationCode.getText().toString().trim().isEmpty()) {
                    Toast.prompt(this, "验证码不能为空");
                    phoneNums = "";
                    return;
                }
//                if (!etPhoneNumber.getText().toString().trim().equals(phoneNums)) {
//                    Toast.prompt(this, "获取验证码的手机号与该手机号不同");
//                    phoneNums = "";
//                    return;
//                }
                RequestMap params1 = new RequestMap();
                params1.put("phone", phoneNums);
                params1.put("code", code);
                setParams(NetUrl.LOGIN, params1, 1);
        }
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId) {
            case 0:
                try {
                    if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                        Toast.prompt(this, JSON.parseObject(response).getString("发送验证码失败，请稍后重试"));
                        return;
                    } else {
                        android.widget.Toast.makeText(this, "获取验证码成功", android.widget.Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 1:
                try {
                    if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                        Toast.prompt(this, "验证码错误！");
                        return;
                    } else {
                        android.widget.Toast.makeText(this, "登录成功", android.widget.Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        String uid = JSON.parseObject(response).getString("infor");

                        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
                        SharedPreferences sp1 = getSharedPreferences("isfirst", Context.MODE_PRIVATE);
                        SharedPreferences sp2 = getSharedPreferences("phone", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sp.edit();
                        SharedPreferences.Editor editor1 = sp1.edit();
                        SharedPreferences.Editor editor2 = sp2.edit();
                        editor.putString("uid", uid);
                        editor.commit();
                        editor1.putString("isfirst", "1");
                        editor1.commit();
                        editor2.putString("phone",phoneNums );
                        editor2.commit();
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
        }
    }

    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        int phoneVerify = VerifyPhoneNumber.isMatchLength(phoneNums, 11);
        if (phoneVerify == Constant.PHONE_NULL) {
            Toast.prompt(this, "手机号不能为空");
            return false;
        } else if (phoneVerify == Constant.PHONE_LENGTH_ERROR) {
            Toast.prompt(this, "手机号位数不对");
            return false;
        } else if (phoneVerify == Constant.PHONE_FORMAT_ERROR) {
            Toast.prompt(this, "手机号格式不对");
            return false;
        } else {
            return true;
        }
    }
}
