package net.cpsec.zfwx.guodian.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.TouPiaoDetailActivity;
import net.cpsec.zfwx.guodian.adapter.TouPiaoListAdapter;
import net.cpsec.zfwx.guodian.entity.TouPiaoList;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TouPiaoFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {
    private YRecycleview yRecycleview;
    private TouPiaoListAdapter adapter;
    private List<TouPiaoList.InforBean> touPiaoInforBeen;
    private boolean isRefreshState = true;//是否刷新

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tou_piao, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.QINGNIAN_ZHISHENG_TOUPIAO, params, 0);
    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_sheng_toupiao);
        yRecycleview.setRefreshAndLoadMoreListener(this);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setAdapter() {
        if (isRefreshState && null != touPiaoInforBeen) {
            adapter = new TouPiaoListAdapter(getActivity(), touPiaoInforBeen);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new TouPiaoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), TouPiaoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("vote_id",touPiaoInforBeen.get(position-1).getVote_id()+"" );
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            TouPiaoList list = JSON.parseObject(response, TouPiaoList.class);
            Debugging.debugging("position      =      " + (null == list));
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                touPiaoInforBeen = list.getInfor();
                Debugging.debugging("positionLists      =   " + (touPiaoInforBeen.size()));
            } else {
                touPiaoInforBeen = list.getInfor();
//                moreanLiMeiWenInfors = anLiMeiWenBean.getInfor();
//                anLiMeiWenInfors.addAll(moreanLiMeiWenInfors);
            }
            setAdapter();
        } catch (Exception e) {
            Toast.prompt(getActivity(), "数据异常");
        }

    }

    @Override
    public void onRefresh() {
        isRefreshState = true;
        yRecycleview.setReFreshComplete();
        initData();
    }

    @Override
    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yRecycleview.setNoMoreData(true);
    }
}
