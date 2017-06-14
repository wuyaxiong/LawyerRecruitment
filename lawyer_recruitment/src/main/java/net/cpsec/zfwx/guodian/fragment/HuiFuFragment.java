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
public class HuiFuFragment extends BaseFragment {
    private YRecycleview yRecycleview;
    private JiaoLiuAdapter adapter;
    private boolean isRefreshState = true;//是否刷新
    private List<QuanBuInfor> quanbuInfor;
    private List<QuanBuInfor> morequanbuInfor;
    private QuanBuBean quanbuBean;

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
        //params.put("uid",""+ MyApplication.UID);
        //因为接口问题，先用全部帖子接口    CENTER_HUIFU_TIEZI
        setParams(NetUrl.QINGNIAN_JIJIAOLIU_SHIJIAN, params, 1);
    }
    //数据请求成功后数据处理方法
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            quanbuBean = JSON.parseObject(response, QuanBuBean.class);
            Debugging.debugging("position      =      " + (null == quanbuBean));
            Debugging.debugging("我的收藏贴子      =      " + quanbuBean.toString());
            if (isRefreshState) {
                yRecycleview.setReFreshComplete();
                quanbuInfor = quanbuBean.getInfor();
                Debugging.debugging("positionLists      =   " + (quanbuBean.getInfor().toString()));
            } else {
                morequanbuInfor = quanbuBean.getInfor();
                quanbuInfor.addAll(morequanbuInfor);
            }
            setAdapter();
        } catch (Exception e) {
            Toast.prompt(getActivity(), "数据异常");
        }
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
                bundle.putString("from","3");
                bundle.putString("username3",infor.getUsername().toString());
                bundle.putString("content3",infor.getContent());
                bundle.putString("time3",infor.getTime()+"");
                bundle.putString("title3",infor.getTitle());
                bundle.putString("image3",infor.getImage());
                bundle.putString("userpic3",infor.getUserpic());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
