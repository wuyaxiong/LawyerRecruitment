package net.cpsec.zfwx.guodian.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.TieZiDetailActivity;
import net.cpsec.zfwx.guodian.adapter.WenDaAdapter;
import net.cpsec.zfwx.guodian.entity.MyWenDaBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;

/**
 * 个人中心-我提问页面
 */
public class WoTiWenFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {
    private YRecycleview yRecycleview;
    private WenDaAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private MyWenDaBean bean;
    private List<MyWenDaBean.InforBean> inforBeen;
    int pos;
    String uid;
    private ImageView iv_wenda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wo_ti_wen, container, false);
        SharedPreferences sp = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        initView(v);
        return v;
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("uid", uid);
        setParams(NetUrl.CENTER_TIWEN, params, 0);

    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_wenda_wotiwen);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        yRecycleview.setRefreshAndLoadMoreListener(this);
        iv_wenda = (ImageView) v.findViewById(R.id.iv_wenda);
        initData();
    }

    private void setAdapter() {
        if (isRefreshState && null != inforBeen) {
            adapter = new WenDaAdapter(getActivity(), inforBeen);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new WenDaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                pos = inforBeen.get(position - 1).getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //数据请求成功后数据处理方法
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
        if ("-400".equals(JSON.parseObject(response).getString("code"))) {
            iv_wenda.setVisibility(View.VISIBLE);
            yRecycleview.setVisibility(View.GONE);
        } else {
            iv_wenda.setVisibility(View.GONE);
            yRecycleview.setVisibility(View.VISIBLE);
            bean = JSON.parseObject(response, MyWenDaBean.class);
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                inforBeen = bean.getInfor();
            }else {
                inforBeen=bean.getInfor();
            }
            if (bean == null) {
                yRecycleview.setVisibility(View.GONE);
                iv_wenda.setVisibility(View.VISIBLE);
            } else {
                yRecycleview.setVisibility(View.VISIBLE);
                iv_wenda.setVisibility(View.GONE);
                setAdapter();
            }
        }
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
        yRecycleview.setNoMoreData(true);
        isRefreshState = false;
        initData();
    }
}
