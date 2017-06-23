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
import net.cpsec.zfwx.guodian.activity.ZhengCeTongZhiDetailActivity;
import net.cpsec.zfwx.guodian.adapter.QingChunFenXiangAdapter;
import net.cpsec.zfwx.guodian.entity.ZhengCeTongZhiBean;
import net.cpsec.zfwx.guodian.entity.ZhengCeTongZhiInfor;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TongZhiFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {

    private YRecycleview yRecycleview;
    private QingChunFenXiangAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<ZhengCeTongZhiInfor> tongzhiInfor;
    private List<ZhengCeTongZhiInfor> moretongzhiInfor;
    private ZhengCeTongZhiBean tongzhiBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_tong_zhi, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.QINCHUN_TOINGZHI, params, 0);
    }
    private void setAdapter() {
        if (isRefreshState && null != tongzhiInfor) {
            adapter = new QingChunFenXiangAdapter(getActivity(), tongzhiInfor);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new QingChunFenXiangAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), ZhengCeTongZhiDetailActivity.class);
                ZhengCeTongZhiInfor infor=tongzhiInfor.get(position-1);
                Bundle bundle=new Bundle();
                bundle.putString("from","1");
                bundle.putString("aid",infor.getId()+"");
                intent.putExtras(bundle);
                startActivity(intent);
                //    Log.d("闯过去的图片地址", "getImage: "+infor.getImage());
            }
        });
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            tongzhiBean = JSON.parseObject(response, ZhengCeTongZhiBean.class);
            Debugging.debugging("position      =      " + (null == tongzhiBean));
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                tongzhiInfor = tongzhiBean.getInfor();
                Debugging.debugging("positionLists      =   " + (tongzhiInfor.size()));
            } else {
                tongzhiInfor = tongzhiBean.getInfor();
//                moretongzhiInfor = tongzhiBean.getInfor();
//                tongzhiInfor.addAll(moretongzhiInfor);
            }
            Debugging.debugging("HJIFHIBFIWIFBIWB+++++++"+tongzhiInfor.size());
            setAdapter();
        } catch (Exception e) {
            Toast.prompt(getActivity(), "数据异常");
        }
    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_sahre_tongzhi);
        yRecycleview.setRefreshAndLoadMoreListener(this);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
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
