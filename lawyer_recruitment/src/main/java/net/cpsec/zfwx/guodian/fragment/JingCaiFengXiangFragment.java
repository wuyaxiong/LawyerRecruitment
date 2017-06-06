package net.cpsec.zfwx.guodian.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import net.cpsec.zfwx.guodian.R;


public class JingCaiFengXiangFragment extends BaseFragment implements View.OnClickListener {
    //两个fragment
    private Fragment f1;
    private Fragment f2;

    //两个按钮
    private Button foot1;
    private Button foot2;

    View v;
    TabLayout tabLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_jing_cai_feng_xiang, container, false);
        initView(v);
        return v;
    }


    private void initView(View v) {
        foot1 = (Button) v.findViewById(R.id.btn_1);
        foot2 = (Button) v.findViewById(R.id.btn_2);
        foot1.setOnClickListener(this);
        foot2.setOnClickListener(this);

        //第一次初始化首页默认显示第一个fragment
        initFragment1();

    }

    //显示第一个fragment
    private void initFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();//原代码用的是getSupportFragmentManager

        //第一中方式（add）初始化fragment并添加到事务中，如果为null就new一个
//        if(f1==null){
//            f1 = new HuoDongFragment();
//            transaction.add(R.id.jcfx_framelayout,f1);
//        }

        //第二种方式(replace)，初始化fragment
        if(f1 == null){
           f1 = new HuoDongFragment();
        }
        transaction.replace(R.id.jcfx_framelayout, f1);
        //隐藏所有fragment
        hideFragment(transaction);
        //显示需要的fragment
        transaction.show(f1);

        //提交事务
        transaction.commit();
    }
    private void initFragment2() {
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//        if(f2 != null){
//            f2 = new JuZhenFragment();
//            transaction.add(R.id.jcfx_framelayout, f2);
//        }
        //第二种方式(replace)，初始化fragment
        if(f2 == null){
            f2 = new JuZhenFragment();
        }
        transaction.replace(R.id.jcfx_framelayout, f2);

        hideFragment(transaction);

        transaction.show(f2);

        transaction.commit();
    }

    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction) {
        if(f1 != null){
            transaction.hide(f1);
        }
        if(f2 != null){
            transaction.hide(f2);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == foot1){
            initFragment1();
        }else if(v == foot2){
            initFragment2();
        }
    }


}
