package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.manager.RequestMap;
import com.makeramen.roundedimageview.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.HaoYouBean;
import net.cpsec.zfwx.guodian.entity.PersonInfoBean;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;

import java.util.Map;


public class XiangXiZiLiaoActivity extends BaseActivity implements View.OnClickListener {
    private PersonInfoBean personInfoBean;
    private RequestMap params1;
    private HaoYouBean haoyouBean;
    String uid;
    String phone;
    ImageView addImageView;
    private RoundedImageView head;
    private TextView tv_name, tv_sex, tv_birth, tv_mianmao, tv_instruction, tv_company, tv_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang_xi_zi_liao);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        Intent intent = getIntent();
        if (intent != null) {
            // String info = intent.getStringExtra("info");
            phone = intent.getStringExtra("phone");
//            try {
//                personInfoBean = new Gson().fromJson(info, PersonInfoBean.class);
//            }catch (Exception e){
//                Toast.makeText(this, "数据异常", Toast.LENGTH_SHORT).show();
//                finish();
            //  }
            initView();
        }
        initData();
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("phone", phone);
        //TODO 模拟UID
        params.put("uid", uid);
        setParams(NetUrl.HAOYOU_DETAIL, params, 2);
    }

    private void initView() {
        addImageView = (ImageView) findViewById(R.id.info_add);
        addImageView.setOnClickListener(this);
        ((ImageView) findViewById(R.id.back)).setOnClickListener(this);
        head = (RoundedImageView) findViewById(R.id.mCenter_iv_myName);
        tv_name = (TextView) findViewById(R.id.info_name);
        tv_sex = (TextView) findViewById(R.id.info_sex);
        tv_birth = (TextView) findViewById(R.id.info_brother);
        tv_company = (TextView) findViewById(R.id.info_cname);
        tv_mianmao = (TextView) findViewById(R.id.info_mianmao);
        tv_instruction = (TextView) findViewById(R.id.info_introduction);
        tv_address = (TextView) findViewById(R.id.info_address);
//        ((TextView)findViewById(R.id.info_address)).setText(personInfoBean.getInfor().getRe().getAddress());
//        ((TextView)findViewById(R.id.info_brother)).setText(personInfoBean.getInfor().getRe().getBirth());
//        ((TextView)findViewById(R.id.info_cname)).setText(personInfoBean.getInfor().getRe().getCname());
//        ((TextView)findViewById(R.id.info_introduction)).setText((String)personInfoBean.getInfor().getRe().getIntroduction());
//        ((TextView)findViewById(R.id.info_name)).setText(personInfoBean.getInfor().getRe().getUsername());
//        ((TextView)findViewById(R.id.info_sex)).setText((personInfoBean.getInfor().getRe().getSex()==1)?"男":"女");
//        if (1==personInfoBean.getInfor().getNum()) {
//            addImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.btn_follow_hover));
//            addImageView.setEnabled(false);
//        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.info_add:
                //TODO  发起好友申请
                params1 = new RequestMap();
                params1.put("friend_group_id", "1");
                params1.put("phone",phone);
                //TODO 模拟UID
                params1.put("uid", uid);
                setParams(NetUrl.ADD_FRIEND_APPLY, params1, 1);
                break;
        }

    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId) {
            case 1:
                try {
                    JSONObject jsonObject = JSONObject.parseObject(response);
                    Toast.makeText(this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(XiangXiZiLiaoActivity.this,MainActivity.class));
                } catch (Exception e) {
                }
                break;
            case 2:
                try {
                    haoyouBean = JSON.parseObject(response, HaoYouBean.class);
                    Debugging.debugging("position      =      " + (null == haoyouBean));
                    ImageLoader.getInstance().displayImage("http://" + haoyouBean.getInfor().getRe().getUserpic(), head);
                    tv_name.setText(haoyouBean.getInfor().getRe().getUsername());
                    tv_birth.setText(haoyouBean.getInfor().getRe().getBirth());
                    tv_mianmao.setText(haoyouBean.getInfor().getRe().getBackground());
                    tv_company.setText(haoyouBean.getInfor().getRe().getCname());
                    tv_instruction.setText((CharSequence) haoyouBean.getInfor().getRe().getIntroduction());
                    tv_address.setText(haoyouBean.getInfor().getRe().getAddress());
                    int sex = haoyouBean.getInfor().getRe().getSex();
                    if (sex == 0) {
                        tv_sex.setText("男");
                    } else {
                        tv_sex.setText("女");
                    }
                    int isFriend = haoyouBean.getInfor().getNum();
                    if (isFriend == 1) {
                        addImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_follow_hover));
                        addImageView.setEnabled(false);
                    }else {
                        addImageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.btn_follow_nor));
                        addImageView.setEnabled(true);
                    }
                } catch (Exception e) {
                }
                break;
        }
    }
}
