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
import net.cpsec.zfwx.lawyer_recruitment.adapter.JianYiAdapter;
import net.cpsec.zfwx.lawyer_recruitment.entity.JianYiBean;
import net.cpsec.zfwx.lawyer_recruitment.entity.JianYiInfor;
import net.cpsec.zfwx.lawyer_recruitment.ui.YRecycleview;
import net.cpsec.zfwx.lawyer_recruitment.utils.Debugging;
import net.cpsec.zfwx.lawyer_recruitment.utils.NetUrl;
import net.cpsec.zfwx.lawyer_recruitment.utils.Toast;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class JianYiFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {

    private YRecycleview yRecycleview;
    private JianYiAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<JianYiInfor> jianyiLists;
    private List<JianYiInfor> morejianyiLists;
    private JianYiBean jianyiBean;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_jian_yi, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_sheng_jianyi);
       yRecycleview.setRefreshAndLoadMoreListener(this);
        initData();
    }
    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.QINGNIAN_JIANYI, params, 0);

    }
    private void setAdapter() {
        if (isRefreshState && null != jianyiLists) {
            adapter = new JianYiAdapter(getActivity(), jianyiLists);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            jianyiBean = JSON.parseObject(response, JianYiBean.class);
            Debugging.debugging("position      =      " + (null == jianyiBean));
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                jianyiLists = jianyiBean.getInfor();
                Debugging.debugging("positionLists      =   " + (jianyiLists.size()));
            } else {
                morejianyiLists = jianyiBean.getInfor();
                jianyiLists.addAll(morejianyiLists);
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
      //  Toast.prompt(getActivity(), "刷新完成。测试阶段");
    }

    @Override
    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yRecycleview.setNoMoreData(true);
        //Toast.prompt(getActivity(), "没有更多数据。测试阶段");
    }
}
