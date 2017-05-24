package net.cpsec.zfwx.lawyer_recruitment;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.android.volley.manager.RequestManager;

import net.cpsec.zfwx.lawyer_recruitment.utils.ScreenManager;

public class MyApplication extends Application {
    // 是否是调试状态
    public static final Boolean isDebuggingState = true;
    // 对activity栈的处理
    public static Boolean FINISH_INDEX = false;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        RequestManager.getInstance().init(MyApplication.this);
        if (Build.VERSION.SDK_INT >=24) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

    }
}