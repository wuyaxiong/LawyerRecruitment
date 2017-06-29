package net.cpsec.zfwx.guodian.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.adapter.ZhuanJiaAdapter;
import net.cpsec.zfwx.guodian.entity.ZhuanJiaBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaibuzhuanjiaFragment extends BaseFragment implements View.OnClickListener, YRecycleview.OnRefreshAndLoadMoreListener{
    private YRecycleview yRecycleview;
    private ZhuanJiaAdapter adapter;
    private int resultCode = 3;
    private boolean isRefreshState = true;//是否刷新
    private List<ZhuanJiaBean.InforBean> zhuanJiaInfor;
    private ZhuanJiaBean zhuanJiaBean;


    public WaibuzhuanjiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_waibuzhuanjia, container, false);
        initData();
        initView(v);
        return v;
    }

    private void initView(View v) {
        yRecycleview= (YRecycleview) v.findViewById(R.id.sendzj_waibuzhuanjia);
        yRecycleview.setRefreshAndLoadMoreListener(this);

    }
    //请求数据
    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.WAIBU_ZHUANJIA, params, 0);
    }
    //数据请求成功后数据处理方法
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            zhuanJiaBean = JSON.parseObject(response, ZhuanJiaBean.class);
            Log.e("专家页面", "zhuanJiaBean"+zhuanJiaBean.toString() );

            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                zhuanJiaInfor = zhuanJiaBean.getInfor();
            } else {
                zhuanJiaInfor = zhuanJiaBean.getInfor();
            }
            setAdapter();
        } catch (Exception e) {
            Toast.prompt(getActivity(), "数据异常");
        }
    }


    private void setAdapter() {
        if (isRefreshState && null != zhuanJiaInfor) {
            adapter = new ZhuanJiaAdapter(getActivity(), zhuanJiaInfor);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }


        adapter.setOnItemClickListener(new ZhuanJiaAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ZhuanJiaBean.InforBean infor= zhuanJiaInfor.get(position-1);
                Log.e("专家页面", "infor.getUsername()"+infor.getUsername() );
                Intent mIntent = new Intent();
                mIntent.putExtra("eid", infor.getId()+"");
                mIntent.putExtra("zhuanjia_name", infor.getUsername());
                // 设置结果，并进行传送
                getActivity().setResult(resultCode, mIntent);
                getActivity().finish();
            }
        });
    }


    public void onRefresh() {
        isRefreshState = true;
        yRecycleview.setReFreshComplete();
        //Toast.prompt(this, "刷新完成。测试阶段");
    }

    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yRecycleview.setNoMoreData(true);
        // Toast.prompt(this, "没有更多数据。测试阶段");
    }

    @Override
    public void onClick(View v) {

    }
}
