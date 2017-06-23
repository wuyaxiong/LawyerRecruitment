package net.cpsec.zfwx.guodian.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.QingChunFenXiangDetailActivity;
import net.cpsec.zfwx.guodian.adapter.HuoDongFenXiangAdapter;
import net.cpsec.zfwx.guodian.entity.HuoDongFenXiang;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;

public class HuoDongFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener{
    private YRecycleview yRecycleview;
    private HuoDongFenXiangAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private HuoDongFenXiang huodong;
    private List<HuoDongFenXiang.InforBean> infors;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View v =inflater.inflate(R.layout.fragment_huodong, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.rv_huodong);
        yRecycleview.setRefreshAndLoadMoreListener(this);
        initData();
    }
    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.HUODONG_FENXIANG, params, 0);
    }

    private void setAdapter() {
        if (isRefreshState && null != infors) {
            adapter = new HuoDongFenXiangAdapter(getActivity(), infors);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new HuoDongFenXiangAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), QingChunFenXiangDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("from","2");
                bundle.putString("gid",huodong.getInfor().get(position-1).getId()+"");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            huodong = JSON.parseObject(response, HuoDongFenXiang.class);
            Debugging.debugging("position      =      " + (null == huodong));
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                infors=huodong.getInfor();
                Debugging.debugging("positionLists      =   " + (huodong.getInfor().size()));
            } else {
                infors=huodong.getInfor();
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
        //    Toast.prompt(getActivity(), "刷新完成。测试阶段");
    }

    @Override
    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yRecycleview.setNoMoreData(true);
        //Toast.prompt(getActivity(), "没有更多数据。测试阶段");
    }



}
