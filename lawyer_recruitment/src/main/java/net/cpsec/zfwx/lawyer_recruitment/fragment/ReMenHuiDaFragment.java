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
import net.cpsec.zfwx.lawyer_recruitment.adapter.JiaoLiuAdapter;
import net.cpsec.zfwx.lawyer_recruitment.entity.QuanBuBean;
import net.cpsec.zfwx.lawyer_recruitment.entity.QuanBuInfor;
import net.cpsec.zfwx.lawyer_recruitment.ui.YRecycleview;
import net.cpsec.zfwx.lawyer_recruitment.utils.Debugging;
import net.cpsec.zfwx.lawyer_recruitment.utils.NetUrl;
import net.cpsec.zfwx.lawyer_recruitment.utils.Toast;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReMenHuiDaFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {
    private YRecycleview yRecycleview;
    private JiaoLiuAdapter adapter;

    private boolean isRefreshState = true;//是否刷新
    private List<QuanBuInfor> quanbuInfor;
    private List<QuanBuInfor> morequanbuInfor;
    private QuanBuBean quanbuBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_re_men_hui_da, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_jiaoliu_remenhuida);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        yRecycleview.setRefreshAndLoadMoreListener(this);
    }
    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.QINGNIAN_JIJIAOLIU_QUANBU, params, 0);
    }
    private void setAdapter() {
        if (isRefreshState && null != quanbuInfor) {
            adapter = new JiaoLiuAdapter(getActivity(), quanbuInfor);
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
            quanbuBean = JSON.parseObject(response, QuanBuBean.class);
            Debugging.debugging("position      =      " + (null == quanbuBean));
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                quanbuInfor = quanbuBean.getInfor();
                Debugging.debugging("positionLists      =   " + (quanbuInfor.size()));
            } else {
                morequanbuInfor = quanbuBean.getInfor();
                quanbuInfor.addAll(morequanbuInfor);
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
        //Toast.prompt(getActivity(), "没有更多数据。测试阶段");
    }

}
