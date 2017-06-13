package net.cpsec.zfwx.guodian.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.fragment.HuLilanFragment;
import net.cpsec.zfwx.guodian.fragment.JiaoLiuFragment;
import net.cpsec.zfwx.guodian.fragment.ShareFragment;
import net.cpsec.zfwx.guodian.fragment.ShengFragment;
import net.cpsec.zfwx.guodian.fragment.TongXunLuFragment;

import static com.alibaba.tcms.client.ClientRegInfo.APP_KEY;
import static net.cpsec.zfwx.guodian.R.id.tv_jiaoliu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvHuLian, tvTongXun, tvJiaoLiu, tvSheng, tvShare;
    private FragmentManager fm;
    private HuLilanFragment huLianFragment;
    private JiaoLiuFragment jiaoLiuFragment;
    private ShengFragment shengFragment;
    private ShareFragment shareFragment;
    private TongXunLuFragment tongXunLuFragment;
    //private Fragment xianshanghulianFragment;
    //private Fragment lianxirenFragment;
//    private ImageView iv_left, iv_right;
//    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setSoftInputMode
                (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//     for (int i = 0; i < ScreenManager.getScreenManager().activityStack.size() - 1; i++) {
//          ScreenManager.getScreenManager().getActivityByIndex(i).finish();
//       }
//        MyApplication.FINISH_INDEX = true;
        fm = getSupportFragmentManager();
        initView();
        initFragment();
        tvJiaoLiu.setSelected(true);
      //  tv_title.setText("线上互联");
        fm.beginTransaction().add(R.id.fl_container, jiaoLiuFragment).commit();


        initAl();
    }
    private void initAl(){
        //此实现不一定要放在Application onCreate中
        final String userid = "17600382402";//用户手机号
        String password = "1234";//手机收到的验证码
        //此对象获取到后，保存为全局对象，供APP使用
        //此对象跟用户相关，如果切换了用户，需要重新获取
        YWIMKit mIMKit = YWAPI.getIMKitInstance(userid, APP_KEY);

        //开始登录


        IYWLoginService loginService = mIMKit.getLoginService();
        YWLoginParam loginParam = YWLoginParam.createLoginParam(userid, password);
        loginService.login(loginParam, new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
            }

            @Override
            public void onProgress(int arg0) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onError(int errCode, String description) {
                //如果登录失败，errCode为错误码,description是错误的具体描述信息
            }
        });
        //获取最近回话列表
        //xianshanghulianFragment = mIMKit.getConversationFragment();
        //获取联系人界面
        //lianxirenFragment= mIMKit.getContactsFragment();
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
        tvJiaoLiu = (TextView) findViewById(tv_jiaoliu);
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
            case tv_jiaoliu:
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


}
