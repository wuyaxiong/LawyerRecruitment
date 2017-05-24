package net.cpsec.zfwx.lawyer_recruitment.fragment;


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

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.activity.MyCenterActivity;
import net.cpsec.zfwx.lawyer_recruitment.activity.SouSuoActivity;
import net.cpsec.zfwx.lawyer_recruitment.activity.XinShengTiWenActivity;


public class ShengFragment extends Fragment implements View.OnClickListener {
    private ImageView iv_action_left,iv_actionbar_right,iv_tiwen;
    private ViewPager viewPager;
    private TextView tv_title;
    View v;
    TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v=inflater.inflate(R.layout.fragment_sheng, container, false);
      //  return inflater.inflate(R.layout.fragment_sheng, container, false);
        initView(v);
        return v;

    }

    private void initView(View v) {
        iv_tiwen= (ImageView) v.findViewById(R.id.iv_sheng_tiwen);
        iv_tiwen.setOnClickListener(this);
        viewPager = (ViewPager) v.findViewById(R.id.vp_sheng);
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
        tv_title.setText("青年之声");
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
                startActivity(new Intent(getActivity(), SouSuoActivity.class));
                break;
            case R.id.iv_sheng_tiwen:
                startActivity(new Intent(getActivity(), XinShengTiWenActivity.class));
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
                    fragment = new XinShengFragment();
                    break;
                case 1:
                    fragment = new JianYiFragment();
                    break;
                case 2:
                    fragment = new TouPiaoFragment();
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
                    return "青年心声";
                case 1:
                    return "合理化建议";
                case 2:
                    return "投票调查";
            }
            return null;
        }
    }
}
