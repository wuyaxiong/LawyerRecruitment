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
import net.cpsec.zfwx.guodian.adapter.JiaoLiuAdapter;
import net.cpsec.zfwx.guodian.entity.QuanBuBean;
import net.cpsec.zfwx.guodian.entity.QuanBuInfor;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class WoHuiDaFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener {
    private YRecycleview yRecycleview;
    private JiaoLiuAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<QuanBuInfor> quanbuInfor;
    private List<QuanBuInfor> morequanbuInfor;
    private QuanBuBean quanbuBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_wo_hui_da, container, false);
        initView(v);
        initData();
        return  v;
    }

    private void initData() {
        RequestMap params = new RequestMap();
        setParams(NetUrl.QINGNIAN_JIJIAOLIU_QUANBU, params, 0);

    }

    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_wenda_wohuida     );
        yRecycleview.setRefreshAndLoadMoreListener(this);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setAdapter() {
        if (isRefreshState && null != quanbuInfor) {
            adapter = new JiaoLiuAdapter(getActivity(), quanbuInfor);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }
        adapter.setOnItemClickListener(new JiaoLiuAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getActivity(), WenTiXiangQiActivity.class);
                QuanBuInfor infor=quanbuInfor.get(position-1);
                Bundle bundle=new Bundle();
                bundle.putString("from","4");
                bundle.putString("username4",infor.getUsername().toString());
                bundle.putString("content4",infor.getContent());
                bundle.putString("time4",infor.getTime()+"");
                bundle.putString("title4",infor.getTitle());
                bundle.putString("image4",infor.getImage());
                bundle.putString("userpic4",infor.getUserpic());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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
    }

    @Override
    public void onLoadMore() {
        yRecycleview.setNoMoreData(true);
        isRefreshState = false;
        initData();
    }
}
