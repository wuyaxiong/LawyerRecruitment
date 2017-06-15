package net.cpsec.zfwx.guodian.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.activity.FaTieActivity;
import net.cpsec.zfwx.guodian.activity.MyCenterActivity;
import net.cpsec.zfwx.guodian.activity.SouSuoTieZiActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class JiaoLiuFragment extends Fragment implements View.OnClickListener {

    private ImageView iv_action_left,iv_actionbar_right,iv_fatie;
    private TextView tv_title;
    private ViewPager viewPager;
    TabLayout tabLayout;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v=inflater.inflate(R.layout.fragment_jiao_liu, container, false);
        initView(v);
        return v;
    }
    private void initView(View v) {
        iv_fatie= (ImageView) v.findViewById(R.id.iv_jiaoliu_fatie);
        viewPager = (ViewPager) v.findViewById(R.id.vp_jiaoliu);
        tabLayout= (TabLayout) v.findViewById(R.id.tl_tab);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        iv_action_left= (ImageView) v.findViewById(R.id.iv_back);
        iv_actionbar_right= (ImageView) v.findViewById(R.id.iv_more);
        iv_action_left.setImageResource(R.drawable.icon_people);
        iv_actionbar_right.setImageResource(R.drawable.icon_sreach);
        tv_title= (TextView) v.findViewById(R.id.tv_title);
        tv_title.setText("青年交流");
        iv_fatie.setOnClickListener(this);
        iv_action_left.setOnClickListener(this);
        iv_actionbar_right.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                Intent intent = new Intent(getActivity(), MyCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_more:
                startActivity(new Intent(getActivity(), SouSuoTieZiActivity.class));
                break;
            case R.id.iv_jiaoliu_fatie:
                startActivity(new Intent(getActivity(), FaTieActivity.class));
                break;
        }
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new Fragment();
            switch (position) {
                case 0:
                    fragment = new QuanBuFragment();
                    break;
                case 1:
                    fragment = new ReMenHuiDaFragment();
                    break;
                case 2:
                    fragment = new WeiDaWenTiFragment();
                    break;

            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "全部";
                case 1:
                    return "热门回答";
                case 2:
                    return "未答问题";
            }
            return null;
        }
    }
}
