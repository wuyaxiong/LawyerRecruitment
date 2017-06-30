package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.CenterTieZiAdapter;
import net.cpsec.zfwx.guodian.entity.ShouCangBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;

/**
 * 我收藏的帖子页面
 */
public class ShouCangActivity extends BaseActivity implements View.OnClickListener, YRecycleview.OnRefreshAndLoadMoreListener {
    private ImageView iv_back;
    private YRecycleview yRecycleview;
    private CenterTieZiAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<ShouCangBean.InforBean> inforBeen;
    private List<ShouCangBean.InforBean> moreInforBean;
    private ShouCangBean shouCangBean;
    ShouCangBean.InforBean infor;
    int pos;
    String uid;
    private ImageView iv_NO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shou_cang);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        initView();
        initData();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        yRecycleview = (YRecycleview) findViewById(R.id.sc_tiezilist);
        yRecycleview.setRefreshAndLoadMoreListener(this);
        iv_NO = (ImageView) findViewById(R.id.iv_wenda);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    //数据请求
    private void initData() {
        RequestMap params = new RequestMap();
        params.put("uid", uid);
        //因为接口问题，先用全部帖子接口   CENTER_GUANZHU_TIEZI
        setParams(NetUrl.CENTER_SHOUCANG, params, 1);
    }

    //数据请求成功后数据处理方法
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            if (!"200".equals(JSON.parseObject(response).getString("code"))) {
                yRecycleview.setVisibility(View.GONE);
                iv_NO.setVisibility(View.VISIBLE);
            } else {
                yRecycleview.setVisibility(View.VISIBLE);
                iv_NO.setVisibility(View.GONE);
                shouCangBean = JSON.parseObject(response, ShouCangBean.class);
                if (isRefreshState) {
                    yRecycleview.setReFreshComplete();
                    inforBeen = shouCangBean.getInfor();
                    Debugging.debugging("positionLists      =   " + (shouCangBean.getInfor().toString()));
                } else {
                    moreInforBean = shouCangBean.getInfor();
                    inforBeen.addAll(moreInforBean);
                }
                setAdapter();
            }
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }


    private void setAdapter() {
        if (isRefreshState && null != inforBeen) {
            adapter = new CenterTieZiAdapter(this, inforBeen);
            yRecycleview.setLayoutManager(new LinearLayoutManager(this));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }


        adapter.setOnTitleClickListener(new CenterTieZiAdapter.OnTitleClickListener() {
            @Override
            public void onTitleClick(String id, int position) {
                Intent intent = new Intent(ShouCangActivity.this, TieZiDetailActivity.class);
                infor = inforBeen.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        adapter.setHeadClickListener(new CenterTieZiAdapter.OnHeadClickListener() {
            @Override
            public void onHeadClick(String id, int position) {
                Intent intent = new Intent(ShouCangActivity.this, XiangXiZiLiaoActivity.class);
                infor = inforBeen.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("phone", infor.getPhone());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnPicClickListener(new CenterTieZiAdapter.OnPicClickListener() {
            @Override
            public void onPicClick(String id, int position) {
                Intent intent = new Intent(ShouCangActivity.this, TieZiDetailActivity.class);
                infor = inforBeen.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnLLClickListener(new CenterTieZiAdapter.OnLLClickListener() {
            @Override
            public void onLLClick(String id, int position) {
                Intent intent = new Intent(ShouCangActivity.this, TieZiDetailActivity.class);
                infor = inforBeen.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRefresh() {
        isRefreshState = true;
        yRecycleview.setReFreshComplete();
        initData();
    }

    //上拉加载
    @Override
    public void onLoadMore() {
        isRefreshState = false;
        initData();
        //刷新之后在最底下
        yRecycleview.setNoMoreData(true);
    }
}
