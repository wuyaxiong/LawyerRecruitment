package net.cpsec.zfwx.lawyer_recruitment.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.adapter.AnLiMeiWenAdapter;
import net.cpsec.zfwx.lawyer_recruitment.entity.AnLiMeiWenBean;
import net.cpsec.zfwx.lawyer_recruitment.entity.AnLiMeiWenInfor;
import net.cpsec.zfwx.lawyer_recruitment.ui.YRecycleview;
import net.cpsec.zfwx.lawyer_recruitment.utils.Debugging;
import net.cpsec.zfwx.lawyer_recruitment.utils.NetUrl;
import net.cpsec.zfwx.lawyer_recruitment.utils.Toast;

import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class AnLiFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {
    private YRecycleview yRecycleview;
    private AnLiMeiWenAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<AnLiMeiWenInfor> anLiMeiWenInfors;
    private List<AnLiMeiWenInfor> moreanLiMeiWenInfors;
    private AnLiMeiWenBean anLiMeiWenBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_an_li, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.QINCHUN_MEIWEN, params, 0);
    }

    private void setAdapter() {
        if (isRefreshState && null != anLiMeiWenInfors) {
            adapter = new AnLiMeiWenAdapter(getActivity(), anLiMeiWenInfors);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_sahre_anlimeiwen);
        yRecycleview.setRefreshAndLoadMoreListener(this);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            anLiMeiWenBean = JSON.parseObject(response, AnLiMeiWenBean.class);
            Debugging.debugging("position      =      " + (null == anLiMeiWenBean));
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                anLiMeiWenInfors = anLiMeiWenBean.getInfor();
                Debugging.debugging("positionLists      =   " + (anLiMeiWenInfors.size()));
            } else {
                moreanLiMeiWenInfors = anLiMeiWenBean.getInfor();
                anLiMeiWenInfors.addAll(moreanLiMeiWenInfors);
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

