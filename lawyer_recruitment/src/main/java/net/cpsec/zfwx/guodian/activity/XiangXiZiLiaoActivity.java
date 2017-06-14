package net.cpsec.zfwx.guodian.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.android.volley.manager.RequestMap;
import com.google.gson.Gson;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.PersonInfoBean;
import net.cpsec.zfwx.guodian.utils.NetUrl;

import java.io.Serializable;
import java.util.Map;


public class XiangXiZiLiaoActivity extends BaseActivity implements View.OnClickListener {
    private PersonInfoBean personInfoBean;
    private RequestMap params1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_xi_zi_liao);
        Intent intent = getIntent();
        if (intent!=null){
            String info = intent.getStringExtra("info");
            try {
                personInfoBean = new Gson().fromJson(info, PersonInfoBean.class);
            }catch (Exception e){
                Toast.makeText(this, "数据异常", Toast.LENGTH_SHORT).show();
                finish();
            }
            initView();
        }
    }

    private void initView() {
        ((ImageView)findViewById(R.id.info_add)).setOnClickListener(this);
        ((ImageView)findViewById(R.id.back)).setOnClickListener(this);
        ((TextView)findViewById(R.id.info_address)).setText(personInfoBean.getInfor().getRe().getAddress());
        ((TextView)findViewById(R.id.info_brother)).setText(personInfoBean.getInfor().getRe().getBirth());
        ((TextView)findViewById(R.id.info_cname)).setText(personInfoBean.getInfor().getRe().getCname());
        ((TextView)findViewById(R.id.info_introduction)).setText((String)personInfoBean.getInfor().getRe().getIntroduction());
        ((TextView)findViewById(R.id.info_name)).setText(personInfoBean.getInfor().getRe().getUsername());
        ((TextView)findViewById(R.id.info_sex)).setText((personInfoBean.getInfor().getRe().getSex()==1)?"男":"女");


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.info_add:
                //TODO  发起好友申请
                params1  = new RequestMap();
                params1.put("friend_group_id","1");
                params1.put("phone", personInfoBean.getInfor().getRe().getPhone());
                //TODO 模拟UID
                params1.put("uid", "329");
                setParams(NetUrl.ADD_FRIEND_APPLY, params1, 1);
                break;
        }

    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
    try{
        JSONObject jsonObject = JSONObject.parseObject(response);
        Toast.makeText(this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
    }catch(Exception e){

        }
    }
}
