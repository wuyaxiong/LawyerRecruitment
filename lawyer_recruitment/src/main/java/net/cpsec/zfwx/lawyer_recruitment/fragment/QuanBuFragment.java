package net.cpsec.zfwx.lawyer_recruitment.fragment;


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

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.adapter.QingChunJiaoLiuAdapter;
import net.cpsec.zfwx.lawyer_recruitment.ui.YRecycleview;
import net.cpsec.zfwx.lawyer_recruitment.utils.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuanBuFragment extends Fragment implements YRecycleview.OnRefreshAndLoadMoreListener, View.OnClickListener {
    private TextView tv_quanbu, tv_paixu, tv_pop_quanbu, tv_pop_bencengji, tv_pop_bendanwei;
    private RelativeLayout rl_quanbu, rl_paixu;
    private View popView;
    private PopupWindow window;
    private LayoutInflater mInflater;
    private LinearLayout ll_actionbar, pop_quanbu, pop_bencengji, pop_bendanwei;
    int restult = 0;
    private YRecycleview yRecycleview;
    private QingChunJiaoLiuAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_quan_bu, container, false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        tv_quanbu = (TextView) v.findViewById(R.id.tv_qunbu);
        rl_quanbu = (RelativeLayout) v.findViewById(R.id.rl_jiaoliu_quanbu);
        rl_paixu = (RelativeLayout) v.findViewById(R.id.rl_jiaoliupaixu);
        rl_quanbu.setOnClickListener(this);
        rl_paixu.setOnClickListener(this);
        ll_actionbar = (LinearLayout) v.findViewById(R.id.ll_quanbu_acationbar);
        yRecycleview = (YRecycleview) v.findViewById(R.id.yrv_jiaoliu_quanbu);
        adapter = new QingChunJiaoLiuAdapter(getActivity());
        yRecycleview.setRefreshAndLoadMoreListener(this);
        yRecycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        yRecycleview.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        yRecycleview.setReFreshComplete();
        Toast.prompt(getActivity(), "刷新完成。测试阶段");
    }

    @Override
    public void onLoadMore() {
        yRecycleview.setNoMoreData(true);
        Toast.prompt(getActivity(), "没有更多数据。测试阶段");
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
                vTouch=popView.findViewById(R.id.v_touch);
                pop_quanbu.setOnClickListener(this);
                pop_bencengji.setOnClickListener(this);
                pop_bendanwei.setOnClickListener(this);
                vTouch.setOnClickListener(this);
                if (tv_pop_quanbu.getText().toString().equals(tv_quanbu.getText().toString())) {
                    pop_quanbu.setSelected(true);
                    pop_bencengji.setSelected(false);
                    pop_bendanwei.setSelected(false);
                }else if(tv_pop_bencengji.getText().toString().equals(tv_quanbu.getText().toString())){
                    pop_quanbu.setSelected(false);
                    pop_bencengji.setSelected(true);
                    pop_bendanwei.setSelected(false);
                }else{
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
                break;
            case R.id.ll_popupwindow_bendanwei:
                if (null != window)
                    window.dismiss();
                tv_quanbu.setText(tv_pop_bendanwei.getText().toString());
                pop_quanbu.setSelected(false);
                pop_bencengji.setSelected(false);
                pop_bendanwei.setSelected(true);
                break;
            case R.id.v_touch:
                window.dismiss();
                break;
            case R.id.rl_jiaoliupaixu:
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
