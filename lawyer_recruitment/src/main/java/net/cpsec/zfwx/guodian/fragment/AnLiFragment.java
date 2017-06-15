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
import net.cpsec.zfwx.guodian.activity.QingChunFenXiangDetailActivity;
import net.cpsec.zfwx.guodian.adapter.AnLiMeiWenAdapter;
import net.cpsec.zfwx.guodian.entity.AnLiMeiWenBean;
import net.cpsec.zfwx.guodian.entity.AnLiMeiWenInfor;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

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
        adapter.setOnItemClickListener(new AnLiMeiWenAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), QingChunFenXiangDetailActivity.class);
                AnLiMeiWenInfor infor=anLiMeiWenInfors.get(position-1);
                Bundle bundle=new Bundle();
                bundle.putString("from","1");
               bundle.putString("gid",position+"");
//                bundle.putString("content3",infor.getContent());
//                bundle.putString("time3",infor.getTime()+"");
//                bundle.putString("title3",infor.getTitle());
//                bundle.putString("image3",infor.getImage());
//                bundle.putString("userpic3",infor.getUserpic());
                intent.putExtras(bundle);
                startActivity(intent);
            //    Log.d("闯过去的图片地址", "getImage: "+infor.getImage());
            }
        });

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
                anLiMeiWenInfors=anLiMeiWenBean.getInfor();
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

