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
import net.cpsec.zfwx.guodian.activity.XiangXiZiLiaoActivity;
import net.cpsec.zfwx.guodian.adapter.WoHuiDaAdapter;
import net.cpsec.zfwx.guodian.entity.WoHuiDaBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;

/**
 * 个人中心--我回答页面
 */
public class WoHuiDaFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {
    private YRecycleview yRecycleview;
    private WoHuiDaAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<List<WoHuiDaBean.InforBean>> inforBeen;
    private WoHuiDaBean huiDaBean;
   // WoHuiDaBean.InforBean infor;
    int pos;
    String uid;
    private ImageView iv_wenda;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_wo_hui_da, container, false);
        SharedPreferences sp = getActivity().getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp.getString("uid", "");
        initView(v);
        return v;
    }

    private void initData() {
        RequestMap params = new RequestMap();
        params.put("uid", uid);
        setParams(NetUrl.CENTER_WOHUIDA, params, 0);

    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_wenda_wohuida);
        yRecycleview.setRefreshAndLoadMoreListener(this);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        iv_wenda = (ImageView) v.findViewById(R.id.iv_wenda);
        initData();
    }

    private void setAdapter() {
        if (isRefreshState && null != huiDaBean) {
            adapter = new WoHuiDaAdapter(getActivity(), huiDaBean);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnTitleClickListener(new WoHuiDaAdapter.OnTitleClickListener() {
            @Override
            public void onTitleClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
               // infor = inforBeen.get(position).get(position);
                //pos = infor.getId();
                pos=huiDaBean.getInfor().get(position).get(0).getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        adapter.setHeadClickListener(new WoHuiDaAdapter.OnHeadClickListener() {
            @Override
            public void onHeadClick(String id, int position) {
                Intent intent = new Intent(getActivity(), XiangXiZiLiaoActivity.class);
               // infor = inforBeen.get(position).get(position);
                Bundle bundle = new Bundle();
                bundle.putString("phone", huiDaBean.getInfor().get(position).get(0).getPhone());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnLLClickListener(new WoHuiDaAdapter.OnLLClickListener() {
            @Override
            public void onLLClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                // infor = inforBeen.get(position).get(position);
                //pos = infor.getId();
                pos=huiDaBean.getInfor().get(position).get(0).getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnPicClickListener(new WoHuiDaAdapter.OnPicClickListener() {
            @Override
            public void onPicClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                // infor = inforBeen.get(position).get(position);
                //pos = infor.getId();
                pos=huiDaBean.getInfor().get(position).get(0).getId();
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
        if ("-400".equals(JSON.parseObject(response).getString("code"))){
            yRecycleview.setVisibility(View.GONE);
            iv_wenda.setVisibility(View.VISIBLE);
        }else {
            try {
                huiDaBean = JSON.parseObject(response, WoHuiDaBean.class);
            if (huiDaBean == null) {
                yRecycleview.setVisibility(View.GONE);
                iv_wenda.setVisibility(View.VISIBLE);
            }
                if (isRefreshState) {
                    yRecycleview.setReFreshComplete();
                   // inforBeen = huiDaBean.getInfor().get();
                } else {
                   // inforBeen = huiDaBean.getInfor();
                }
                setAdapter();
            } catch (Exception e) {
                Toast.prompt(getActivity(), "数据异常");
            }
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
