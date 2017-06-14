package net.cpsec.zfwx.guodian.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import com.alibaba.fastjson.JSON;
import com.android.volley.manager.RequestManager;
import com.android.volley.manager.RequestMap;

import net.cpsec.zfwx.guodian.utils.Debugging;
import net.cpsec.zfwx.guodian.utils.Toast;
import net.cpsec.zfwx.guodian.utils.SharePreferenceEditor;

import java.util.Map;

import jetbrick.util.StringUtils;

public class BaseFragment extends Fragment implements RequestManager.RequestListener {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        SharePreferenceEditor sharePreferenceEditor = SharePreferenceEditor.newInstance(context);
        sharedPreferences = sharePreferenceEditor.getSharedPreferences();
        editor = sharePreferenceEditor.getEditor();
    }

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
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
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
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && ni.isConnectedOrConnecting();
    }

    // 不带消息头session的post请求
    public void setParams(String url, RequestMap params, int actionId) {
        if (getNetworkType() == 0 || !isNetworkConnected()) {
            Toast.prompt(getActivity(), "暂无网络，请检查您的网络！");
        } else {
            RequestManager.getInstance().post(url, params, this, actionId);
        }
    }

    // 带消息头session的post请求
    public void setParams(String url, RequestMap params, final Map<String, String> headers, int actionId) {
        if (getNetworkType() == 0 || !isNetworkConnected()) {
            Toast.prompt(getActivity(), "暂无网络，请检查您的网络！");
        } else {
            RequestManager.getInstance().post(url, params, this, headers, actionId);
        }
    }

    @Override
    public void onRequest() {
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        Debugging.debugging("response" + "=" + response + "\nurl" + "=" + url + "\nactionId" + "=" + actionId);
//        try {
//            if ("SUCCESS".equals(JSON.parseObject(response).getString("flag")))
//                Toast.prompt(getActivity(), JSON.parseObject(response).getString("description"));
//        } catch (Exception e) {
//            Toast.prompt(getActivity(), "数据异常");
//        }
    }

    @Override
    public void onError(String errorMsg, String url, int actionId) {
        Debugging.debugging("errorMsg" + "=" + errorMsg + "\nurl" + "=" + url + "\nactionId" + "=" + actionId);
    }
}
