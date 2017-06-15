package net.cpsec.zfwx.guodian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.JiaoLiuAdapter;
import net.cpsec.zfwx.guodian.entity.QuanBuBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.Map;

public class SouSuoTieZiActivity extends BaseActivity implements YRecycleview.OnRefreshAndLoadMoreListener {
private TextView tv_back;
    private Button btn1,btn2,btn3;
    private EditText editText;
    private boolean isRefreshState = true;//是否刷新
   // private SearchTieZiBean tieZiBean;
    private QuanBuBean tieziBean;
    private YRecycleview yRecycleview;
    private JiaoLiuAdapter adapter;
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sou_suo);
        initView();
    }
    private void setAdapter() {
        if (isRefreshState && null != tieziBean.getInfor()) {
            adapter = new JiaoLiuAdapter(this, tieziBean.getInfor());
            yRecycleview.setLayoutManager(new LinearLayoutManager(this));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnTitleClickListener(new JiaoLiuAdapter.OnTitleClickListener() {
            @Override
            public void onTitleClick(String id, int position) {
                Intent intent = new Intent(SouSuoTieZiActivity.this, TieZiDetailActivity.class);
                Debugging.debugging("position+++++++++++++++++++++++" + position);
                pos = tieziBean.getInfor().get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setHeadClickListener(new JiaoLiuAdapter.OnHeadClickListener() {
            @Override
            public void onHeadClick(String id, int position) {
                Intent intent = new Intent(SouSuoTieZiActivity.this, XiangXiZiLiaoActivity.class);
            //    pos = tieziBean.getInfor().get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putString("phone", tieziBean.getInfor().get(position).getPhone());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
    private void initView() {
        yRecycleview= (YRecycleview) findViewById(R.id.yrv_search_tiezi);
        yRecycleview.setRefreshAndLoadMoreListener(this);
        tv_back= (TextView) findViewById(R.id.tv_sousuo_back);
        tv_back.setOnClickListener(this);
        btn1= (Button) findViewById(R.id.btn_search01);
        btn1.setOnClickListener(this);
        btn2= (Button) findViewById(R.id.btn_search02);
        btn2.setOnClickListener(this);
        btn3= (Button) findViewById(R.id.btn_search03);
        btn3.setOnClickListener(this);
        editText= (EditText) findViewById(R.id.et_search);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_sousuo_back:
                finish();
                break;
            case R.id.btn_search01:
                RequestMap params1 = new RequestMap();
                params1.put("title", editText.getText().toString());
                setParams(NetUrl.SEARCH_TITLE, params1, 1);
                break;
            case R.id.btn_search02:
                RequestMap params2 = new RequestMap();
                params2.put("company", editText.getText().toString());
                setParams(NetUrl.SEARCH_DANWEI, params2, 2);
                break;
            case R.id.btn_search03:
                RequestMap params3 = new RequestMap();
                params3.put("name", editText.getText().toString());
                setParams(NetUrl.SEARCH_LABEL, params3, 3);
                break;
        }
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId){
            case 1:
                try {
                    if (!"200".equals(JSON.parseObject(response).getString("code"))){
                        Toast.prompt(this, "抱歉，未搜索到相关帖子!");
                    }else{
                        tieziBean = JSON.parseObject(response, QuanBuBean.class);
                        Debugging.debugging("position      =      " + (null == tieziBean));
                        if (isRefreshState) {
                            yRecycleview.setReFreshComplete();
                            Debugging.debugging("positionLists      =   " + (tieziBean.getInfor().toString()));
                        } else {
//                        morequanbuInfor = quanbuBean.getInfor();
//                        quanbuInfor.addAll(morequanbuInfor);
                        }
                        setAdapter();
                    }

                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 2:
                try {
                    if (!"200".equals(JSON.parseObject(response).getString("code"))){
                        Toast.prompt(this, "抱歉,公司名称不存在!");
                    }else {
                    tieziBean = JSON.parseObject(response, QuanBuBean.class);
                    Debugging.debugging("position      =      " + (null == tieziBean));
                    if (isRefreshState) {
                        yRecycleview.setReFreshComplete();
                        Debugging.debugging("positionLists      =   " + (tieziBean.getInfor().toString()));
                    } else {
//                        morequanbuInfor = quanbuBean.getInfor();
//                        quanbuInfor.addAll(morequanbuInfor);
                    }}
                    setAdapter();
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
            case 3:
                try {
                    if (!"200".equals(JSON.parseObject(response).getString("code"))){
                        Toast.prompt(this, "抱歉，未搜索到相关帖子!");
                    }else {
                        tieziBean = JSON.parseObject(response, QuanBuBean.class);
                        Debugging.debugging("position      =      " + (null == tieziBean));
                        if (isRefreshState) {
                            yRecycleview.setReFreshComplete();
                            Debugging.debugging("positionLists      =   " + (tieziBean.getInfor().toString()));
                        } else {
//                        morequanbuInfor = quanbuBean.getInfor();
//                        quanbuInfor.addAll(morequanbuInfor);
                        }
                    }
                        setAdapter();
                } catch (Exception e) {
                    Toast.prompt(this, "数据异常");
                }
                break;
        }
    }

    @Override
    public void onRefresh() {
        isRefreshState = true;
        yRecycleview.setReFreshComplete();
       // initData();
    }

    @Override
    public void onLoadMore() {
        isRefreshState = false;
        //initData();
        yRecycleview.setNoMoreData(true);
        //Toast.prompt(getActivity(), "没有更多数据。测试阶段");
    }
}
