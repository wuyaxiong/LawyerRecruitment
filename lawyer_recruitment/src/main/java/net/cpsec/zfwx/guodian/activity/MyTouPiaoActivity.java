package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.TouPiaoAdapter;
import net.cpsec.zfwx.guodian.entity.TouPiaoBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;


public class MyTouPiaoActivity extends BaseActivity implements View.OnClickListener, YRecycleview.OnRefreshAndLoadMoreListener {
    private ImageView iv_back;
    private YRecycleview yRecycleview;
    private List<TouPiaoBean.InforBean> touPiaoInfor;
    private TouPiaoBean touPiaoBean;
    private TouPiaoAdapter adapter;
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
            touPiaoBean = JSON.parseObject(response, TouPiaoBean.class);
            Debugging.debugging("position = " + (null == touPiaoBean));
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                touPiaoInfor = touPiaoBean.getInfor();
                Debugging.debugging("touPiaoInfor  =  " + (touPiaoBean.getInfor().toString()));
            } else {
                touPiaoInfor = touPiaoBean.getInfor();
            }
            setAdapter();
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }

    private void setAdapter() {
        if (isRefreshState && null != touPiaoInfor) {
            adapter = new TouPiaoAdapter(this, touPiaoInfor);
            yRecycleview.setLayoutManager(new LinearLayoutManager(this));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        //touPiaoInfor  =  [InforBean{vote_id=1, admin_id=1, title='最美国电人', time=1496735800, description='各分（子）公司、研究院、基层企业员工：
        //目前，由国资委宣传局开展的“最美国电人”征集活动已进入公众投票阶段。在本次评选活动中，以下员工成功入选。为宣传具有国电特色的先进典型，
        // 以典型宣传讲好“国电故事”，请按照有关要求，积极参与投票。'},
        // InforBean{vote_id=2, admin_id=1, title='发表意见', time=1496735800, description='各部门积极发表意见'}]

        adapter.setOnItemClickListener(new TouPiaoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.e("投票点击事件", "onItemClick: " + "投票点击事件");
                Intent intent = new Intent(MyTouPiaoActivity.this, TouPiaoXiangQingActivity.class);
                TouPiaoBean.InforBean infor = touPiaoInfor.get(position - 1);
                Bundle bundle = new Bundle();
                bundle.putString("time", infor.getTime() + "");
                bundle.putString("title", infor.getTitle());
                bundle.putString("description", infor.getDescription());
                bundle.putInt("voter_id", infor.getVote_id());
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
        Toast.prompt(this, "刷新完成。测试阶段");
    }

    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yRecycleview.setNoMoreData(true);
        Toast.prompt(this, "没有更多数据。测试阶段");
    }
}
