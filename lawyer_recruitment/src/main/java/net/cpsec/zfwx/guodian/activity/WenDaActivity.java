package net.cpsec.zfwx.guodian.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.fragment.WoHuiDaFragment;
import net.cpsec.zfwx.guodian.fragment.WoTiWenFragment;
/**
 * 个人中心问答页面
 * */
public class WenDaActivity extends BaseActivity {
    private ImageView iv_back;
    private ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wen_da);
        initView();
    }

    private void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        viewPager= (ViewPager) findViewById(R.id.vp_wenda);
        tabLayout= (TabLayout) findViewById(R.id.tl_tab_wenda);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_back:
                finish();
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
                    fragment = new WoTiWenFragment();
                    break;
                case 1:
                    fragment = new WoHuiDaFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "我提问的";
                case 1:
                    return "我回答的";

            }
            return null;
        }
    }
}
