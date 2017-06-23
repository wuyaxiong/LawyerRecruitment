package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.utils.NetUrl;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AddFriendActivity extends BaseActivity implements View.OnClickListener {
    private ImageView iv_action_left,iv_actionbar_right;
    private TextView tv_title;
    private TextView empty;
    private EditText editText;
    private String phone;
    private RequestMap params1;
    private boolean isFirst=true;
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend2);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid= sp.getString("uid","");

        if (editText==null){
            initView();
        }
    }

    private void initView() {
        editText = (EditText) findViewById(R.id.edit_add_friend);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if ((actionId == 0 || actionId == 3) && event != null) {
                    if (isPhone()) {
                        if (isFirst){

                            params1  = new RequestMap();
                            params1.put("phone", phone);
                            //TODO 模拟UID
                            params1.put("uid", uid);
                            setParams(NetUrl.ADD_FRIEND, params1, 1);
                        }
                        isFirst=!isFirst;
                    }else{
                        Toast.makeText(AddFriendActivity.this, "您输入的不是手机号", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }
                return false;

            }

        });
        iv_action_left= (ImageView) findViewById(R.id.iv_back);
        iv_actionbar_right= (ImageView) findViewById(R.id.iv_more);
        iv_action_left.setImageResource(R.drawable.back);
        iv_actionbar_right.setVisibility(View.GONE);
        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_title.setText("添加好友");
        iv_action_left.setOnClickListener(this);
        empty = (TextView) findViewById(R.id.empty);

    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            JSONObject jsonObject = JSONObject.parseObject(response);
            Log.e("123", "onSuccess: "+response );
                Toast.makeText(this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, XiangXiZiLiaoActivity.class);
          // intent.putExtra("phone",jsonObject.getString("phone"));
            intent.putExtra("phone",editText.getText().toString());
           // intent.putExtra("info",response);
            startActivity(intent);
        }catch (Exception e){
            empty.setText("该联系人不存在");
            Toast.makeText(this, "该联系人不存在", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean isPhone(){
    phone = editText.getText().toString().trim();
    if ("".equals(phone)){
        return false;
    }
    Pattern p = Pattern
            .compile("^(0|86|17951)?(13[0-9]|15[0-9]|17[0-9]|18[0-9]|14[0-9])[0-9]{8}$");

    Matcher m = p.matcher(phone);

    return m.matches();
}
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
               finish();
                break;

        }
    }
}
