package net.cpsec.zfwx.guodian.utils;

import android.util.Log;

import net.cpsec.zfwx.guodian.MyApplication;


public class Debugging {
    public static void debugging(String content) {
        if (MyApplication.isDebuggingState) {
            Log.i(Constant.DEBUGGING_FLAG, content);
        }
    }
}
