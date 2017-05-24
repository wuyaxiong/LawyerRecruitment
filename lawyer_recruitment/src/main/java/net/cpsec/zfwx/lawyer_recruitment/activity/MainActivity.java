package net.cpsec.zfwx.lawyer_recruitment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.fragment.HuLilanFragment;
import net.cpsec.zfwx.lawyer_recruitment.fragment.JiaoLiuFragment;
import net.cpsec.zfwx.lawyer_recruitment.fragment.ShareFragment;
import net.cpsec.zfwx.lawyer_recruitment.fragment.ShengFragment;
import net.cpsec.zfwx.lawyer_recruitment.fragment.TongXunLuFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvHuLian, tvTongXun, tvJiaoLiu, tvSheng, tvShare;
    private FragmentManager fm;
    private HuLilanFragment huLianFragment;
    private JiaoLiuFragment jiaoLiuFragment;
    private ShengFragment shengFragment;
    private ShareFragment shareFragment;
    private TongXunLuFragment tongXunLuFragment;
//    private ImageView iv_left, iv_right;
//    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//     for (int i = 0; i < ScreenManager.getScreenManager().activityStack.size() - 1; i++) {
//          ScreenManager.getScreenManager().getActivityByIndex(i).finish();
//       }
//        MyApplication.FINISH_INDEX = true;
        fm = getSupportFragmentManager();
        initView();
        initFragment();
        tvHuLian.setSelected(true);
      //  tv_title.setText("线上互联");
        fm.beginTransaction().add(R.id.fl_container, huLianFragment).commit();
    }

    private void initFragment() {
        huLianFragment = new HuLilanFragment();
        jiaoLiuFragment = new JiaoLiuFragment();
        shengFragment = new ShengFragment();
        shareFragment = new ShareFragment();
        tongXunLuFragment = new TongXunLuFragment();
    }

    private void initView() {
//        iv_left = (ImageView) findViewById(R.id.iv_back);
//        iv_right = (ImageView) findViewById(R.id.iv_more);
//        iv_left.setImageResource(R.drawable.icon_people);
//        tv_title = (TextView) findViewById(R.id.tv_title);
        tvHuLian = (TextView) findViewById(R.id.tv_hulian);
        tvJiaoLiu = (TextView) findViewById(R.id.tv_jiaoliu);
        tvSheng = (TextView) findViewById(R.id.tv_sheng);
        tvShare = (TextView) findViewById(R.id.tv_fenxiang);
        tvTongXun = (TextView) findViewById(R.id.tv_tongxun);
        tvHuLian.setOnClickListener(this);
        tvJiaoLiu.setOnClickListener(this);
        tvTongXun.setOnClickListener(this);
        tvShare.setOnClickListener(this);
        tvSheng.setOnClickListener(this);
       // iv_left.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        hideAllFragment();
        switch (view.getId()) {
            case R.id.iv_back:
                Intent intent = new Intent(this, MyCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_hulian:
                tvHuLian.setSelected(true);
                if (!huLianFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.fl_container, huLianFragment).commit();
                    fm.beginTransaction().show(huLianFragment).commit();
                } else {
                    fm.beginTransaction().show(huLianFragment).commit();
                }
//                tv_title.setText("线上互联");
//                iv_right.setImageResource(R.drawable.icon_jiar);
                break;
            case R.id.tv_tongxun:
                tvTongXun.setSelected(true);
                if (!tongXunLuFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.fl_container, tongXunLuFragment).commit();
                    fm.beginTransaction().show(tongXunLuFragment).commit();
                } else {
                    fm.beginTransaction().show(tongXunLuFragment).commit();
                }
//                tv_title.setText("通讯录");
//                iv_right.setImageResource(R.drawable.icon_zuzhi);
                break;
            case R.id.tv_jiaoliu:
                tvJiaoLiu.setSelected(true);
                if (!jiaoLiuFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.fl_container, jiaoLiuFragment).commit();

                    fm.beginTransaction().show(jiaoLiuFragment).commit();
                } else {
                    fm.beginTransaction().show(jiaoLiuFragment).commit();
                }
//                iv_right.setImageResource(R.drawable.icon_sreach);
//                tv_title.setText("青年交流");
                break;
            case R.id.tv_sheng:
                tvSheng.setSelected(true);
                if (!shengFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.fl_container, shengFragment).commit();
                    fm.beginTransaction().show(shengFragment).commit();
                } else {
                    fm.beginTransaction().show(shengFragment).commit();
                }
//                tv_title.setText("青年之声");
//                iv_right.setImageResource(R.drawable.icon_sreach);
                break;
            case R.id.tv_fenxiang:
                tvShare.setSelected(true);
                if (!shareFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.fl_container, shareFragment).commit();
                    fm.beginTransaction().show(shareFragment).commit();
                } else {
                    fm.beginTransaction().show(shareFragment).commit();
                }
//                tv_title.setText("青春分享");
//                iv_right.setImageResource(R.drawable.icon_sreach);
                break;
        }
    }

    private void hideAllFragment() {
        fm.beginTransaction().hide(huLianFragment).commit();
        fm.beginTransaction().hide(jiaoLiuFragment).commit();
        fm.beginTransaction().hide(tongXunLuFragment).commit();
        fm.beginTransaction().hide(shareFragment).commit();
        fm.beginTransaction().hide(shengFragment).commit();

        tvHuLian.setSelected(false);
        tvTongXun.setSelected(false);
        tvSheng.setSelected(false);
        tvJiaoLiu.setSelected(false);
        tvShare.setSelected(false);
    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        String s=tv_title.getText().toString();
//        switch (s){
//            case "线上互联":
//                tvHuLian.setSelected(true);
//                fm.beginTransaction().show(huLianFragment).commit();
//                break;
//            case "通讯录":
//                tvTongXun.setSelected(true);
//                fm.beginTransaction().show(tongXunLuFragment).commit();
//                break;
//            case "青年交流":
//                tvJiaoLiu.setSelected(true);
//                fm.beginTransaction().show(jiaoLiuFragment).commit();
//
//                break;
//            case "青年之声":
//                tvSheng.setSelected(true);
//                fm.beginTransaction().show(shengFragment).commit();
//                break;
//            case "青春分享":
//                tvShare.setSelected(true);
//                fm.beginTransaction().show(shareFragment).commit();
//                break;
//        }
//    }
}
