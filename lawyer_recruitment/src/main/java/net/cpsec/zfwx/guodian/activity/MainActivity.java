package net.cpsec.zfwx.guodian.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.mobileim.IYWLoginService;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.YWLoginParam;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.contact.IYWContact;
import com.alibaba.mobileim.contact.IYWContactService;
import com.alibaba.mobileim.contact.IYWCrossContactProfileCallback;
import com.alibaba.mobileim.lib.model.contact.Contact;
import com.android.volley.manager.RequestMap;
import com.google.gson.Gson;

import net.cpsec.zfwx.guodian.R;
import net.cpsec.zfwx.guodian.entity.GetFriendQueueInfoBean;
import net.cpsec.zfwx.guodian.fragment.HuLilanFragment;
import net.cpsec.zfwx.guodian.fragment.JiaoLiuFragment;
import net.cpsec.zfwx.guodian.fragment.ShareFragment;
import net.cpsec.zfwx.guodian.fragment.ShengFragment;
import net.cpsec.zfwx.guodian.fragment.TongXunLuFragment;
import net.cpsec.zfwx.guodian.utils.NetUrl;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.cpsec.zfwx.guodian.R.id.tv_jiaoliu;

public class  MainActivity extends BaseActivity implements View.OnClickListener {
    private TextView tvHuLian, tvTongXun, tvJiaoLiu, tvSheng, tvShare;
    private FragmentManager fm;
    private HuLilanFragment huLianFragment;
    private JiaoLiuFragment jiaoLiuFragment;
    private ShengFragment shengFragment;
    private ShareFragment shareFragment;
    private TongXunLuFragment tongXunLuFragment;
    private List<GetFriendQueueInfoBean.InforBean> allDataList;
    private Fragment xianshanghulianFragment;
    private static String userPhone ;
    private String password = "1234";//百川密码
    private final static int  NOTGROUP=1;
    private final static int   THISWORK=2;
    private final static int   OTHERWORK=3;
    private final static int   GROUPED=4;
    private String uid;
    public static Activity context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        getWindow().setSoftInputMode
                (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN|
                        WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_main);
        SharedPreferences sp = getSharedPreferences("phone", Context.MODE_PRIVATE);
        userPhone= sp.getString("phone","");
        SharedPreferences sp1 = getSharedPreferences("uid", Context.MODE_PRIVATE);
        uid = sp1.getString("uid", "");
        Log.e("123", "onCreate: "+userPhone);
        fm = getSupportFragmentManager();
        initView();
        initFragment();
        tvJiaoLiu.setSelected(true);
        //  tv_title.setText("线上互联");
        fm.beginTransaction().add(R.id.fl_container, jiaoLiuFragment).commit();
        initAl();
    }

    private long exitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if((System.currentTimeMillis()-exitTime) > 2000){
                Toast.prompt(this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public static YWIMKit getMyImKit(){
        //此实现不一定要放在Application onCreate中

        //此对象获取到后，保存为全局对象，供APP使用
        //此对象跟用户相关，如果切换了用户，需要重新获取
        return YWAPI.getIMKitInstance(userPhone, "23893323");
    }
    private void initAl(){
        //开始登录
        IYWLoginService loginService = this.getMyImKit().getLoginService();
        YWLoginParam loginParam = YWLoginParam.createLoginParam(userPhone, password);
        loginService.login(loginParam, new IWxCallback() {

            @Override
            public void onSuccess(Object... arg0) {
                Log.e("123", "onError: "+ "onSuccess");
                initNetData();
            }

            @Override
            public void onProgress(int arg0) {
                // TODO Auto-generated method stub
                Log.e("123", "onError: "+ "onProgress");
            }

            @Override
            public void onError(int errCode, String description) {
                //如果登录失败，errCode为错误码,description是错误的具体描述信息
                Log.e("123", "onError: "+description );
            }
        });
        //获取最近回话列表
        xianshanghulianFragment = this.getMyImKit().getConversationFragment();

    }

    private void initFragment() {
        //huLianFragment = new HuLilanFragment();
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
                if (!xianshanghulianFragment.isAdded()) {
                    fm.beginTransaction().add(R.id.fl_container, xianshanghulianFragment).commit();
                    fm.beginTransaction().show(xianshanghulianFragment).commit();
                } else {
                    fm.beginTransaction().show(xianshanghulianFragment).commit();
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
        fm.beginTransaction().hide(xianshanghulianFragment).commit();
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
    public interface MyTouchListener {
        public void onTouchEvent(MotionEvent event);
    }
    // 保存MyTouchListener接口的列表
    private ArrayList<MyTouchListener> myTouchListeners = new ArrayList<MainActivity.MyTouchListener>();

    /**
     * 提供给Fragment通过getActivity()方法来注册自己的触摸事件的方法
     * @param listener
     */
    public void registerMyTouchListener(MyTouchListener listener) {
        myTouchListeners.add(listener);
    }

    /**
     * 提供给Fragment通过getActivity()方法来取消注册自己的触摸事件的方法
     * @param listener
     */
    public void unRegisterMyTouchListener(MyTouchListener listener) {
        myTouchListeners.remove( listener );
    }

    /**
     * 分发触摸事件给所有注册了MyTouchListener的接口
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        for (MyTouchListener listener : myTouchListeners) {
            listener.onTouchEvent(ev);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void quit(){
        context.finish();
        context=null;
    }
    public List<Message> msgs=new ArrayList<>();
    public Handler myHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            msgs.add(msg);
            if (msgs.size()>=4) {
                msgs.clear();
                setUseName();

            }
        }
    };

    private void initNetData() {
        allDataList=new ArrayList<>();
        allDataList.clear();
        requestGroupInfo(NOTGROUP);
        requestGroupInfo(THISWORK);
        requestGroupInfo(OTHERWORK);
        requestGroupInfo(GROUPED);
    }
    public void requestGroupInfo(int friend_group_id){
        RequestMap params=new RequestMap();
        //TODO 模拟uid
        params.put("uid", uid);
        params.put("friend_group_id",friend_group_id+"");
        setParams(NetUrl.GET_FRIEND, params, friend_group_id);
    }
    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try{
            JSONObject jsonObject = JSONObject.parseObject(response);
            if (!"200".equals(jsonObject.getString("code"))){
                Log.e("123", "onSuccesssssss: "+response );
                myHandler.sendEmptyMessage(1);
                return;
            }

        }catch (Exception e){
            myHandler.sendEmptyMessage(1);
        }
        switch (actionId) {
            case NOTGROUP:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{

                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    List<GetFriendQueueInfoBean.InforBean> infor = infoBean.getInfor();
                    allDataList.addAll(infor);

                    myHandler.sendEmptyMessage(1);
                }catch(Exception e){
                    android.widget.Toast.makeText(MainActivity.this, "未分组解析错误", android.widget.Toast.LENGTH_SHORT).show();
                    myHandler.sendEmptyMessage(1);
                }
                break;
            case THISWORK:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{
                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    allDataList.addAll(infoBean.getInfor());


                    myHandler.sendEmptyMessage(1);
                }catch(Exception e){
                    android.widget.Toast.makeText(MainActivity.this, "本单位解析错误", android.widget.Toast.LENGTH_SHORT).show();
                    myHandler.sendEmptyMessage(1);
                }
                break;
            case OTHERWORK:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{
                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    allDataList.addAll(infoBean.getInfor());

                    myHandler.sendEmptyMessage(1);
                }catch(Exception e){
                    android.widget.Toast.makeText(MainActivity.this, "兄弟单位解析错误", android.widget.Toast.LENGTH_SHORT).show();
                    myHandler.sendEmptyMessage(1);
                }
                break;
            case GROUPED:
                Log.e("notGroupedList", "onSuccess: "+response );
                try{
                    GetFriendQueueInfoBean infoBean = new Gson().fromJson(response, GetFriendQueueInfoBean.class);
                    allDataList.addAll(infoBean.getInfor());

                    myHandler.sendEmptyMessage(1);
                }catch(Exception e){
                    android.widget.Toast.makeText(MainActivity.this, "群组解析错误", android.widget.Toast.LENGTH_SHORT).show();
                    myHandler.sendEmptyMessage(1);
                }
                break;

        }
    }
    public void setUseName(){
        final IYWContactService contactService = MainActivity.getMyImKit().getContactService();
        contactService.setCrossContactProfileCallback(new IYWCrossContactProfileCallback() {

            @Override
            public Intent onShowProfileActivity(String userId, String appKey) {

                //这里支持头像点击事件，需要开发者返回一个Intent
                return null;
            }

            @Override
            public void updateContactInfo(Contact contact) {
                Map<String, String> map = new HashMap<String, String>();
            }

            @Override
            public IYWContact onFetchContactInfo(String userId, final String appKey) {
                if (allDataList!=null&&allDataList.size()>0) {
                    for (int i = 0; i < allDataList.size(); i++) {
                        GetFriendQueueInfoBean.InforBean inforBean = allDataList.get(i);
                        if (inforBean.getPhone().equals(userId)) {
                            return inforBean;
                        }
                    }
                }
                return null;

            }
        });
    }
}
