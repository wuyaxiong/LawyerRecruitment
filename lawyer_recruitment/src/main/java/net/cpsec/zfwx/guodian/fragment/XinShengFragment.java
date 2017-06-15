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
import net.cpsec.zfwx.guodian.activity.WenTiXiangQiActivity;
import net.cpsec.zfwx.guodian.adapter.XinShengTiWenAdapter;
import net.cpsec.zfwx.guodian.entity.ShengBean;
import net.cpsec.zfwx.guodian.entity.ShengDetail;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class XinShengFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {
    private YRecycleview yRecycleview;
    private XinShengTiWenAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<ShengDetail> shengLists;
    private List<ShengDetail> moreshengLists;
    private ShengBean shengBean;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_xin_sheng, container, false);
        initView(v);
        return v;
    }
    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_sheng_xinsheng);
        yRecycleview.setRefreshAndLoadMoreListener(this);
       initData();
    }

    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.QINGNIAN_ZHISHENG, params, 0);
    }
    private void setAdapter() {
        if (isRefreshState && null != shengLists) {
            adapter = new XinShengTiWenAdapter(getActivity(), shengLists);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new XinShengTiWenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), WenTiXiangQiActivity.class);
                ShengDetail infor=shengLists.get(position-1);
                Bundle bundle=new Bundle();
                bundle.putString("from","1");
                bundle.putString("username1",infor.getUsername().toString());
                bundle.putString("content1",infor.getContent());
                bundle.putString("asktime1",infor.getAsktime()+"");
                bundle.putString("image1",infor.getImage());
                bundle.putString("userpic1",infor.getUserpic());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            shengBean = JSON.parseObject(response, ShengBean.class);
            Debugging.debugging("position      =      " + (null == shengBean));
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                shengLists = shengBean.getInfor();
                Debugging.debugging("positionLists      =   " + (shengLists.size()));
            } else {
                moreshengLists = shengBean.getInfor();
                shengLists.addAll(moreshengLists);
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
        //Toast.prompt(getActivity(), "刷新完成。测试阶段");
    }

    @Override
    public void onLoadMore() {
        isRefreshState = false;
        initData();
        yRecycleview.setNoMoreData(true);
       // yRecycleview.setNoMoreData(true);
        //Toast.prompt(getActivity(), "没有更多数据。测试阶段");
    }
}
