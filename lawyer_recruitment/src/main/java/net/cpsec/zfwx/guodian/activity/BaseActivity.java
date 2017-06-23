package net.cpsec.zfwx.guodian.activity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestManager;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.MyApplication;
import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.ScreenManager;
import net.cpsec.zfwx.guodian.utils.Toast;

import java.util.Map;

import jetbrick.util.StringUtils;

public class  BaseActivity extends AppCompatActivity implements View.OnClickListener, RequestManager.RequestListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.FINISH_INDEX = true;
        ScreenManager.getScreenManager().pushActivity(this);
      //  hideSystemNavigationBar();
    }

//    public void setContentView(int layoutResID, Integer titleId) {
//        super.setContentView(layoutResID);
//hideSystemNavigationBar();
//    }

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
     */

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    public int getNetworkType() {
        int netType = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!StringUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }

    /**
     * 检测网络是否可用
     *
     * @return
     */
    public boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ScreenManager.getScreenManager().popActivity();
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.iv_back:
////                MyApplication.FINISH_INDEX = false;
//                finish();
//                break;
//        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MyApplication.FINISH_INDEX = false;
    }

    // 不带消息头session的post请求
    public void setParams(String url, RequestMap params, int actionId) {
        if (getNetworkType() == 0 || !isNetworkConnected()) {
            Toast.prompt(this, "暂无网络，请检查您的网络！");
        } else {
            RequestManager.getInstance().post(url, params, this, actionId);
        }
    }

    // 带消息头session的post请求
    public void setParams(String url, RequestMap params, final Map<String, String> headers, int actionId) {
        if (getNetworkType() == 0 || !isNetworkConnected()) {
            Toast.prompt(this, "暂无网络，请检查您的网络！");
        } else {
            RequestManager.getInstance().post(url, params, this, headers, actionId);
        }
    }

    @Override
    public void finish() {
        super.finish();
        MyApplication.FINISH_INDEX = false;
    }

    @Override
    public void onRequest() {
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        Debugging.debugging("response" + "=" + response + "\nurl" + "=" + url + "\nactionId" + "=" + actionId);
        try {
            if ("msg".equals(JSON.parseObject(response).getString("成功")))
                Toast.prompt(this, JSON.parseObject(response).getString("infor"));
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }

    @Override
    public void onError(String errorMsg, String url, int actionId) {
        Debugging.debugging("errorMsg" + "=" + errorMsg + "\nurl" + "=" + url + "\nactionId" + "=" + actionId);
    }
//隐藏底部虚拟按键并全屏
    private void hideSystemNavigationBar() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View view = this.getWindow().getDecorView();
            view.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
}
