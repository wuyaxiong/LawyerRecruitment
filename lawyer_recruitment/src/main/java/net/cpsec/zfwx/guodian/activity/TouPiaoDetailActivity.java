package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.TouPiaoChooseAdapter;
import net.cpsec.zfwx.guodian.entity.TouPiaoDetailBean;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.Map;

public class TouPiaoDetailActivity extends BaseActivity {
    private TextView tv_title, tv_content;
    private ListView lv_choose;
    private TouPiaoDetailBean touPiaoDetailBean;
    String uid, vote_id, question_id, select_id;
    private ImageView iv_back;
    TouPiaoChooseAdapter adapter;
    private Button btn_complete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tou_piao_detail);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        Intent intent = getIntent();
        vote_id = intent.getStringExtra("vote_id");
        initView();
    }

    private void initView() {
        tv_title = (TextView) findViewById(R.id.tv_toupiao_detail_title);
        tv_content = (TextView) findViewById(R.id.tv_toupiao_detail_content);
        lv_choose = (ListView) findViewById(R.id.lv_toupiao_detail_xuanxiang);
        lv_choose.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        btn_complete = (Button) findViewById(R.id.btn_toupiao_detail_complete);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initData();
        btn_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (select_id==null||select_id.isEmpty()){
                    Toast.prompt(TouPiaoDetailActivity.this, "请选择选项");
                }else {
                    RequestMap params = new RequestMap();
                    params.put("user_id", uid);
                    params.put("vote_id", vote_id);
                    params.put("question_id", question_id);
                    params.put("select_id", select_id);
                    setParams(NetUrl.QINGNIAN_ZHISHENG_TOUPIAO_COMPLETE, params, 2);
                }

            }
        });
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("vote_id", vote_id);
        setParams(NetUrl.QINGNIAN_ZHISHENG_TOUPIAO_DETAIL, params, 1);
    }

    private void setAdapter() {
        if (null != touPiaoDetailBean.getInfor().getQuestions()) {
            adapter = new TouPiaoChooseAdapter(this, touPiaoDetailBean.getInfor().getQuestions());
            lv_choose.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        lv_choose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                question_id = touPiaoDetailBean.getInfor().getQuestions().get(position).getQuestion_id() + "";
                select_id = touPiaoDetailBean.getInfor().getQuestions().get(position).getSelect_id() + "";
            }
        });
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId) {
            case 1:
                try {
                    touPiaoDetailBean = JSON.parseObject(response, TouPiaoDetailBean.class);
                    Debugging.debugging("position      =      " + (null == touPiaoDetailBean));
                    tv_title.setText(touPiaoDetailBean.getInfor().getTitle());
                    tv_content.setText(touPiaoDetailBean.getInfor().getDescription());
                    setAdapter();
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 2:
                Debugging.debugging("JSON.parseObject(response).getString(\"code\"))====="+JSON.parseObject(response).getString("code"));
                if ("200".equals(JSON.parseObject(response).getString("code"))) {
                    Toast.prompt(this, "投票成功！");
                    finish();
                } else if ("-400".equals(JSON.parseObject(response).getString("code"))) {
                    Toast.prompt(this, "您已经投过票了");
                } else {
                    Toast.prompt(this, "投票失败，稍后重试！");

                }
                break;
        }

    }
}
