package net.cpsec.zfwx.guodian.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.MyApplication;
import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.TieZiDetailActivity;
import net.cpsec.zfwx.guodian.activity.XiangXiZiLiaoActivity;
import net.cpsec.zfwx.guodian.adapter.CenterTieZiAdapter;
import net.cpsec.zfwx.guodian.entity.HuiFuBean;
import net.cpsec.zfwx.guodian.ui.YRecycleview;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;
import java.util.List;
import java.util.Map;


/**
 * 个人中心-发帖-我回复的帖子
 */
public class HuiFuFragment extends BaseFragment {
    private YRecycleview yRecycleview;
    private CenterTieZiAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<HuiFuBean.InforBean> inforBeen;
    private List<HuiFuBean.InforBean> moreInforBean;
    private HuiFuBean huiFuBean;
    HuiFuBean.InforBean infor;
    int pos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v =inflater.inflate(R.layout.fragment_hui_fu, container, false);
        initData();
        initView(v);
        return v;
    }
    private void initView(View v) {
        yRecycleview = (YRecycleview) v.findViewById(R.id.hf_tiezilist);
    }
//MyApplication.UID是存在MyApplication中的假数据，后期改成从偏好设置中拿
    private void initData() {
        RequestMap params = new RequestMap();
        params.put("uid",""+ MyApplication.UID);
        setParams(NetUrl.CENTER_HUIFU_TIEZI, params, 1);
    }
    //数据请求成功后数据处理方法
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            huiFuBean = JSON.parseObject(response, HuiFuBean.class);
            if (huiFuBean == null) {
                Toast.prompt(getActivity(), "目前没有数据");
            }
            Log.e("我回复的页面", "onSuccess: "+huiFuBean);
            Debugging.debugging("我的收藏贴子      =      " + huiFuBean.toString());
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                inforBeen = huiFuBean.getInfor();
                Debugging.debugging("positionLists      =   " + (huiFuBean.getInfor().toString()));
            } else {
                moreInforBean = huiFuBean.getInfor();
                inforBeen.addAll(moreInforBean);
            }
            setAdapter();
        } catch (Exception e) {
            Toast.prompt(getActivity(), "数据异常");
        }
    }
    private void setAdapter() {
        if (isRefreshState && null != inforBeen) {
            adapter = new CenterTieZiAdapter(getActivity(), inforBeen);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

        adapter.setOnTitleClickListener(new CenterTieZiAdapter.OnTitleClickListener() {
            @Override
            public void onTitleClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                Debugging.debugging("position+++++++++++++++++++++++" + position);
                infor = inforBeen.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                Log.e("回复界面的artical_id", "artical_id: "+pos);
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        adapter.setHeadClickListener(new CenterTieZiAdapter.OnHeadClickListener() {
            @Override
            public void onHeadClick(String id, int position) {
                Intent intent = new Intent(getActivity(), XiangXiZiLiaoActivity.class);
                infor = inforBeen.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("phone", infor.getPhone());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
