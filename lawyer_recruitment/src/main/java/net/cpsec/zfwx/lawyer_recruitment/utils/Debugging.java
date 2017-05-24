package net.cpsec.zfwx.lawyer_recruitment.utils;

import android.util.Log;

import net.cpsec.zfwx.lawyer_recruitment.MyApplication;


public class Debugging {
    public static void debugging(String content) {
        if (MyApplication.isDebuggingState) {
            Log.i(Constant.DEBUGGING_FLAG, content);
        }
    }
}
