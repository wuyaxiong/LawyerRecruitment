package net.cpsec.zfwx.guodian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.utils.Constant;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.utils.VerifyPhoneNumber;

import java.util.Map;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_login;
    private EditText etPhoneNumber, etVerificationCode;
    private String phoneNums,code;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        imageButton = (ImageButton) findViewById(R.id.ib_login_button);
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
        code=etVerificationCode.getText().toString().trim();
        switch (v.getId()) {
            case R.id.ib_login_button:
                RequestMap params = new RequestMap();
                params.put("phone", phoneNums);
                setParams(NetUrl.REGIST, params, 0);
                break;
            case R.id.btn_login:

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
                if (!etPhoneNumber.getText().toString().trim().equals(phoneNums)) {
                    Toast.prompt(this, "获取验证码的手机号与该手机号不同");
                    phoneNums = "";
                    return;
                }
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
                    if (!"验证码发送成功".equals(JSON.parseObject(response).getString("msg"))) {
                        Toast.prompt(this, JSON.parseObject(response).getString("infor"));
                        return;
                    } else {
                        android.widget.Toast.makeText(this, "获取验证码成功", android.widget.Toast.LENGTH_SHORT).show();
                        //editor.putString(PreferencesStorageKey.USER_PHONENUMBER, JSON.parseObject(response).getString("userphone")).commit();
//                Intent intent = new Intent(this, MainActivity.class);
//                startActivity(intent);
                    }
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 1:
                try {
                    if (!"登录成功".equals(JSON.parseObject(response).getString("msg"))) {
                        Toast.prompt(this, JSON.parseObject(response).getString("infor"));
                        return;
                    } else {
                        android.widget.Toast.makeText(this, "登录成功", android.widget.Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
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
