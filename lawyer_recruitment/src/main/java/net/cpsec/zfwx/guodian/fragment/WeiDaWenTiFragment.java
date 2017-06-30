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
import net.cpsec.zfwx.guodian.activity.TieZiDetailActivity;
import net.cpsec.zfwx.guodian.activity.XiangXiZiLiaoActivity;
import net.cpsec.zfwx.guodian.adapter.ReMenHuiDaAdapter;
import net.cpsec.zfwx.guodian.entity.ReMenBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeiDaWenTiFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {
    private YRecycleview yRecycleview;
    private ReMenHuiDaAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<ReMenBean.InforBean> quanbuInfor;
    private ReMenBean quanbuBean;
    ReMenBean.InforBean infor;
    int pos;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_wei_da_wen_ti, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_jiaoliu_weihuida);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        yRecycleview.setRefreshAndLoadMoreListener(this);
    }
    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.TIEZI_NOANSWER, params, 0);
    }
    private void setAdapter() {
        if (isRefreshState && null != quanbuInfor) {
            adapter = new ReMenHuiDaAdapter(getActivity(), quanbuInfor);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setHeadClickListener(new ReMenHuiDaAdapter.OnHeadClickListener() {
            @Override
            public void onHeadClick(String id, int position) {
                Intent intent = new Intent(getActivity(), XiangXiZiLiaoActivity.class);
                infor = quanbuInfor.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("phone", infor.getPhone());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnTitleClickListener(new ReMenHuiDaAdapter.OnTitleClickListener() {
            @Override
            public void onTitleClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                infor = quanbuInfor.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnPicClickListener(new ReMenHuiDaAdapter.OnPicClickListener() {
            @Override
            public void onPicClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                infor = quanbuInfor.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnLLClickListener(new ReMenHuiDaAdapter.OnLLClickListener() {
            @Override
            public void onLLClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                infor = quanbuInfor.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            quanbuBean = JSON.parseObject(response, ReMenBean.class);
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                quanbuInfor = quanbuBean.getInfor();
            } else {
                quanbuInfor=quanbuBean.getInfor();
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
