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
import net.cpsec.zfwx.guodian.adapter.TouPiaoListAdapter;
import net.cpsec.zfwx.guodian.entity.TouPiaoList;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;


public class MyTouPiaoActivity extends BaseActivity implements View.OnClickListener, YRecycleview.OnRefreshAndLoadMoreListener {
    private ImageView iv_back,iv_none;
    private YRecycleview yRecycleview;
    private TouPiaoListAdapter adapter;
    private List<TouPiaoList.InforBean> touPiaoInforBeen;
    private boolean isRefreshState = true;//是否刷新
    String uid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tou_piao);
        SharedPreferences sp = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        initView();
        initData();
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        yRecycleview = (YRecycleview) findViewById(R.id.my_toupiao_list);
        yRecycleview.setRefreshAndLoadMoreListener(this);
        iv_none= (ImageView) findViewById(R.id.iv_toupiao);
    }

    //请求数据
    private void initData() {
        RequestMap params = new RequestMap();
        params.put("uid", uid);
        //因为接口问题，先用全部帖子接口   CENTER_GUANZHU_TIEZI
        setParams(NetUrl.CENTER_TOUPIAO, params, 0);
    }

    //数据请求成功后数据处理方法
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            TouPiaoList list = JSON.parseObject(response, TouPiaoList.class);
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                touPiaoInforBeen = list.getInfor();
            } else {
                touPiaoInforBeen = list.getInfor();
            }
            if (touPiaoInforBeen==null){
                yRecycleview.setVisibility(View.GONE);
                iv_none.setVisibility(View.VISIBLE);
            }else {
                yRecycleview.setVisibility(View.VISIBLE);
                iv_none.setVisibility(View.GONE);
                setAdapter();
            }
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }

    private void setAdapter() {
        if (isRefreshState && null != touPiaoInforBeen) {
            adapter = new TouPiaoListAdapter(this, touPiaoInforBeen);
            yRecycleview.setLayoutManager(new LinearLayoutManager(this));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new TouPiaoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(MyTouPiaoActivity.this,TouPiaoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("vote_id",touPiaoInforBeen.get(position-1).getVote_id()+"" );
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    public void onRefresh() {
        isRefreshState = true;
        yRecycleview.setReFreshComplete();
    }

    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yRecycleview.setNoMoreData(true);
    }
}
