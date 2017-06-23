package net.cpsec.zfwx.guodian.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.TieZiDetailActivity;
import net.cpsec.zfwx.guodian.activity.XiangXiZiLiaoActivity;
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
public class QuanBuFragment extends BaseFragment implements YRecycleview.OnRefreshAndLoadMoreListener, View.OnClickListener {
    private TextView tv_quanbu, tv_paixu, tv_pop_quanbu, tv_pop_bencengji, tv_pop_bendanwei, tv_pop_shijian, tv_pop_redu;
    private RelativeLayout rl_quanbu, rl_paixu;
    private View popView;
    private PopupWindow window;
    private LayoutInflater mInflater;
    private LinearLayout ll_actionbar, pop_quanbu, pop_bencengji, pop_bendanwei, pop_shijian, pop_redu;
    int restult = 0;
    private YRecycleview yRecycleview;
    private JiaoLiuAdapter adapter;

    private boolean isRefreshState = true;//是否刷新
    private List<QuanBuInfor> quanbuInfor;
    private List<QuanBuInfor> morequanbuInfor;
    private QuanBuBean quanbuBean;
    QuanBuInfor infor;
    int pos;
private RelativeLayout rl_xiala;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quan_bu, container, false);
        initView(v);
        initData();
        return v;
    }

    private void initData() {
        String ur;
        RequestMap params = new RequestMap();
        if (tv_paixu.getText().equals("按时间排序")) {
            ur=NetUrl.QINGNIAN_JIJIAOLIU_SHIJIAN;
          //  setParams(NetUrl.QINGNIAN_JIJIAOLIU_SHIJIAN, params, 1);
        } else {
            ur=NetUrl.QINGNIAN_JIJIAOLIU_REDU;
          //  setParams(NetUrl.QINGNIAN_JIJIAOLIU_REDU, params, 2);
        }
        setParams(ur, params, 1);
        //  setParams(NetUrl.QINGNIAN_JIJIAOLIU_QUANBU, params, 0);
    }
    private void initData1() {
        String url = null;
        RequestMap params = new RequestMap();
        if (tv_quanbu.getText().equals("只看本层级")) {
           url=NetUrl.QINGNIAN_JIJIAOLIU_CENGJI;
           // setParams(NetUrl.QINGNIAN_JIJIAOLIU_CENGJI, params, 3);
        } else {
            url=NetUrl.QINGNIAN_JIJIAOLIU_DANWEI;
        }
        setParams(url, params, 2);
    }
    private void setAdapter() {
        if (isRefreshState && null != quanbuInfor) {
            adapter = new JiaoLiuAdapter(getActivity(), quanbuInfor);
            yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
            yRecycleview.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

//        adapter.setOnItemClickListener(new JiaoLiuAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
////              //  Intent intent=new Intent(getActivity(), WenTiXiangQiActivity.class);
//                infor=quanbuInfor.get(position-1);
//                int  artical_id=infor.getId();
//                Debugging.debugging("artical_id==================="+artical_id);
////                Bundle bundle=new Bundle();
////                bundle.putString("from","3");
////                bundle.putString("username3",infor.getUsername().toString());
////                bundle.putString("content3",infor.getContent());
////                bundle.putString("time3",infor.getTime()+"");
////                bundle.putString("title3",infor.getTitle());
////                bundle.putString("image3",infor.getImage());
////                bundle.putString("userpic3",infor.getUserpic());
////                intent.putExtras(bundle);
////               // startActivity(intent);
////                Log.d("闯过去的图片地址", "getImage: "+infor.getImage());
//
//           }
//        });
        adapter.setOnTitleClickListener(new JiaoLiuAdapter.OnTitleClickListener() {
            @Override
            public void onTitleClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                Debugging.debugging("position+++++++++++++++++++++++" + position);
                infor = quanbuInfor.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setHeadClickListener(new JiaoLiuAdapter.OnHeadClickListener() {
            @Override
            public void onHeadClick(String id, int position) {
              Intent intent = new Intent(getActivity(), XiangXiZiLiaoActivity.class);
                infor = quanbuInfor.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("phone", infor.getPhone());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        adapter.setOnPicClickListener(new JiaoLiuAdapter.OnPicClickListener() {
            @Override
            public void onPicClick(String id, int position) {
                Intent intent = new Intent(getActivity(), TieZiDetailActivity.class);
                infor = quanbuInfor.get(position);
                pos = infor.getId();
                Bundle bundle = new Bundle();
                bundle.putString("artical_id", pos + "");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void initView(View v) {
        tv_quanbu = (TextView) v.findViewById(R.id.tv_qunbu);
        tv_paixu = (TextView) v.findViewById(R.id.tv_paixu);
        rl_quanbu = (RelativeLayout) v.findViewById(R.id.rl_jiaoliu_quanbu);
        rl_paixu = (RelativeLayout) v.findViewById(R.id.rl_jiaoliupaixu);
        rl_quanbu.setOnClickListener(this);
        rl_paixu.setOnClickListener(this);
        ll_actionbar = (LinearLayout) v.findViewById(R.id.ll_quanbu_acationbar);
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_jiaoliu_quanbu);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        yRecycleview.setRefreshAndLoadMoreListener(this);
        rl_xiala= (RelativeLayout) v.findViewById(R.id.rl_tiezi_xiala);
        rl_xiala.setOnClickListener(this);
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        switch (actionId){
            case 1:
                try {
                    quanbuBean = JSON.parseObject(response, QuanBuBean.class);
                    Debugging.debugging("position      =      " + (null == quanbuBean));
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
                break;
            case 2:
                try {
                    quanbuBean = JSON.parseObject(response, QuanBuBean.class);
                    Debugging.debugging("position      =      " + (null == quanbuBean));
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
                break;
        }
//        try {
//            quanbuBean = JSON.parseObject(response, QuanBuBean.class);
//            Debugging.debugging("position      =      " + (null == quanbuBean));
//            if (isRefreshState) {
//                yRecycleview.setReFreshComplete();
//                quanbuInfor = quanbuBean.getInfor();
//                Debugging.debugging("positionLists      =   " + (quanbuBean.getInfor().toString()));
//            } else {
//                morequanbuInfor = quanbuBean.getInfor();
//                quanbuInfor.addAll(morequanbuInfor);
//            }
//            setAdapter();
//        } catch (Exception e) {
//            Toast.prompt(getActivity(), "数据异常");
//        }
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

    @Override
    public void onClick(View v) {
        View vTouch;
        switch (v.getId()) {
            case R.id.rl_jiaoliu_quanbu:
                popView = getActivity().getLayoutInflater().inflate(R.layout.pop_jiaoliu, null);
                pop_quanbu = (LinearLayout) popView.findViewById(R.id.ll_popupwindow_quanbu);
                pop_bencengji = (LinearLayout) popView.findViewById(R.id.ll_popupwindow_bencengji);
                pop_bendanwei = (LinearLayout) popView.findViewById(R.id.ll_popupwindow_bendanwei);
                tv_pop_quanbu = (TextView) popView.findViewById(R.id.tv_popupwindow_quanbu);
                tv_pop_bencengji = (TextView) popView.findViewById(R.id.tv_popupwindow_bencengji);
                tv_pop_bendanwei = (TextView) popView.findViewById(R.id.tv_popupwindow_bendanwei);
                vTouch = popView.findViewById(R.id.v_touch);
                pop_quanbu.setOnClickListener(this);
                pop_bencengji.setOnClickListener(this);
                pop_bendanwei.setOnClickListener(this);
                vTouch.setOnClickListener(this);
                if (tv_pop_quanbu.getText().toString().equals(tv_quanbu.getText().toString())) {
                    pop_quanbu.setSelected(true);
                    pop_bencengji.setSelected(false);
                    pop_bendanwei.setSelected(false);
                } else if (tv_pop_bencengji.getText().toString().equals(tv_quanbu.getText().toString())) {
                    pop_quanbu.setSelected(false);
                    pop_bencengji.setSelected(true);
                    pop_bendanwei.setSelected(false);
                } else {
                    pop_quanbu.setSelected(false);
                    pop_bencengji.setSelected(false);
                    pop_bendanwei.setSelected(true);
                }
                //mInflater = LayoutInflater.from(getContext());
                setPopupWindow();
                break;
            case R.id.ll_popupwindow_quanbu:
                if (null != window)
                    window.dismiss();
                tv_quanbu.setText(tv_pop_quanbu.getText().toString());
                pop_quanbu.setSelected(true);
                pop_bencengji.setSelected(false);
                pop_bendanwei.setSelected(false);
                break;
            case R.id.ll_popupwindow_bencengji:
                if (null != window)
                    window.dismiss();
                tv_quanbu.setText(tv_pop_bencengji.getText().toString());
                pop_quanbu.setSelected(false);
                pop_bencengji.setSelected(true);
                pop_bendanwei.setSelected(false);
                initData1();
                break;
            case R.id.ll_popupwindow_bendanwei:
                if (null != window)
                    window.dismiss();
                tv_quanbu.setText(tv_pop_bendanwei.getText().toString());
                pop_quanbu.setSelected(false);
                pop_bencengji.setSelected(false);
                pop_bendanwei.setSelected(true);
                initData1();
                break;
            case R.id.v_touch:
                window.dismiss();
                break;
            case R.id.rl_jiaoliupaixu:
                popView = getActivity().getLayoutInflater().inflate(R.layout.pop_paixu, null);
                pop_shijian = (LinearLayout) popView.findViewById(R.id.ll_popupwindow_shijian);
                pop_redu = (LinearLayout) popView.findViewById(R.id.ll_popupwindow_redu);
                tv_pop_shijian = (TextView) popView.findViewById(R.id.tv_popupwindow_shijian);
                tv_pop_redu = (TextView) popView.findViewById(R.id.tv_popupwindow_redu);
                vTouch = popView.findViewById(R.id.v_touch_paixu);
                pop_shijian.setOnClickListener(this);
                pop_redu.setOnClickListener(this);
                vTouch.setOnClickListener(this);
                if (tv_pop_shijian.getText().toString().equals(tv_paixu.getText().toString())) {
                    pop_shijian.setSelected(true);
                    pop_redu.setSelected(false);
                    initData();
                } else {
                    pop_shijian.setSelected(false);
                    pop_redu.setSelected(true);
                    initData();
                }
                //mInflater = LayoutInflater.from(getContext());
                setPopupWindow();
                break;
            case R.id.ll_popupwindow_shijian:
                if (null != window)
                    window.dismiss();
                tv_paixu.setText(tv_pop_shijian.getText().toString());
                pop_shijian.setSelected(true);
                pop_redu.setSelected(false);
                initData();
                break;
            case R.id.ll_popupwindow_redu:
                if (null != window)
                    window.dismiss();
                tv_paixu.setText(tv_pop_redu.getText().toString());
                pop_shijian.setSelected(false);
                pop_redu.setSelected(true);
                initData();
                break;
            case R.id.v_touch_paixu:
                window.dismiss();
                break;
            case R.id.rl_tiezi_xiala:
                if (ll_actionbar.getVisibility()==View.VISIBLE){
                    ll_actionbar.setVisibility(View.GONE);
                }else {
                    ll_actionbar.setVisibility(View.VISIBLE);
                }
                break;
        }
    }

    private void setPopupWindow() {
        // TODO: 2016/5/17 创建PopupWindow对象，指定宽度和高度
        window = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        // TODO: 2016/5/17 设置动画
        window.setAnimationStyle(R.style.popup_window_anim);
        // TODO: 2016/5/17 设置背景颜色
        window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00ffffff")));
        // TODO: 2016/5/17 设置可以获取焦点
        window.setFocusable(true);
        // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
        window.setOutsideTouchable(true);
        // TODO：更新popupwindow的状态
        window.update();
        // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
        if (Build.VERSION.SDK_INT < 24) {
            window.showAsDropDown(ll_actionbar, 0, 0);
        } else {
            int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                restult = getResources().getDimensionPixelSize(resourceId);
            }
            window.showAtLocation(getView(), Gravity.NO_GRAVITY, 0, ll_actionbar.getHeight() + restult);
        }
    }


}

