package net.cpsec.zfwx.guodian.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.fragment.NeibuzhuanjiaFragment;
import net.cpsec.zfwx.guodian.fragment.WaibuzhuanjiaFragment;
import net.cpsec.zfwx.guodian.fragment.ZhuzhijiagouFragment;

public class ZhuzhijiagouActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ImageView jiagouback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuzhijiagou);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager=(ViewPager)findViewById(R.id.vp_zhuzhijiagou);
        tabLayout= (TabLayout)findViewById(R.id.jiagou_tab);
        jiagouback=(ImageView)findViewById(R.id.jiagouback_icon);
        setSupportActionBar(toolbar);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
        back();

    }

    private void back() {
        jiagouback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

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
                    fragment = new ZhuzhijiagouFragment();
                    break;
                case 1:
                    fragment = new WaibuzhuanjiaFragment();
                    break;
                case 2:
                    fragment = new NeibuzhuanjiaFragment();
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
                    return "组织架构";
                case 1:
                    return "外部专家";
                case 2:
                    return "内部专家";
            }
            return null;
        }
    }

}
