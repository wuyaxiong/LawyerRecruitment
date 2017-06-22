package net.cpsec.zfwx.guodian.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.fragment.NeibuzhuanjiaFragment;
import net.cpsec.zfwx.guodian.fragment.WaibuzhuanjiaFragment;

public class SendZhuanJiaActivity extends BaseActivity {
//    private YRecycleview yRecycleview;
//    private ZhuanJiaAdapter adapter;
//    private ImageView iv_back;
//    private int resultCode = 3;
//    private boolean isRefreshState = true;//是否刷新
//    private List<ZhuanJiaBean.InforBean> zhuanJiaInfor;
//    private ZhuanJiaBean zhuanJiaBean;

    private ImageView iv_back;
    private ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_zhuan_jia);
        initView();
    }
    private void initView() {
        iv_back= (ImageView) findViewById(R.id.iv_back_zhuanjia);
        iv_back.setOnClickListener(this);
        viewPager= (ViewPager) findViewById(R.id.vp_zhuanjia);
        tabLayout= (TabLayout) findViewById(R.id.tl_tab_zhuanjia);
        SendZhuanJiaActivity.SectionsPagerAdapter adapter = new SendZhuanJiaActivity.SectionsPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
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
                    fragment = new NeibuzhuanjiaFragment();
                    break;
                case 1:
                    fragment = new WaibuzhuanjiaFragment();
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
                    return "内部专家";
                case 1:
                    return "外部专家";

            }
            return null;
        }
    }


//    private void initView() {
//        iv_back = (ImageView) findViewById(R.id.iv_back);
//        iv_back.setOnClickListener(this);
//        yRecycleview= (YRecycleview) findViewById(R.id.sendzj_zhuanjia);
//        yRecycleview.setRefreshAndLoadMoreListener(this);
//
//    }
//    //请求数据
//    private void initData() {
//        RequestMap params = new RequestMap();
//        setParams(NetUrl.ZHUANJIA, params, 0);
//    }
//    //数据请求成功后数据处理方法
//    @Override
//    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
//        super.onSuccess(response, headers, url, actionId);
//        try {
//            zhuanJiaBean = JSON.parseObject(response, ZhuanJiaBean.class);
//            Log.e("专家页面", "zhuanJiaBean"+zhuanJiaBean.toString() );
//
//            if (isRefreshState) {
//                yRecycleview.setReFreshComplete();
//                zhuanJiaInfor = zhuanJiaBean.getInfor();
//            } else {
//                zhuanJiaInfor = zhuanJiaBean.getInfor();
//            }
//            setAdapter();
//        } catch (Exception e) {
//            Toast.prompt(this, "数据异常");
//        }
//    }
//
//
//    private void setAdapter() {
//        if (isRefreshState && null != zhuanJiaInfor) {
//            adapter = new ZhuanJiaAdapter(this, zhuanJiaInfor);
//            yRecycleview.setLayoutManager(new LinearLayoutManager(this));
//            yRecycleview.setAdapter(adapter);
//        } else {
//            adapter.notifyDataSetChanged();
//        }
//
//
//        adapter.setOnItemClickListener(new ZhuanJiaAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ZhuanJiaBean.InforBean infor= zhuanJiaInfor.get(position-1);
//                Log.e("专家页面", "infor.getUsername()"+infor.getUsername() );
//                Intent mIntent = new Intent();
//                mIntent.putExtra("zhuanjia_name", infor.getUsername());
//                // 设置结果，并进行传送
//                SendZhuanJiaActivity.this.setResult(resultCode, mIntent);
//                SendZhuanJiaActivity.this.finish();
//            }
//        });
//    }
//
//
//    public void onRefresh() {
//        isRefreshState = true;
//        yRecycleview.setReFreshComplete();
//        //Toast.prompt(this, "刷新完成。测试阶段");
//    }
//
//    public void onLoadMore() {
//        isRefreshState = false;
//        initData();
//        yRecycleview.setNoMoreData(true);
//       // Toast.prompt(this, "没有更多数据。测试阶段");
//    }
}
