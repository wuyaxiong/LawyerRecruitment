package net.cpsec.zfwx.guodian;

import android.app.Application;
import android.os.Build;
import android.os.StrictMode;

import com.android.volley.manager.RequestManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class MyApplication extends Application {
    // 是否是调试状态
    public static final Boolean isDebuggingState = true;
    // 对activity栈的处理
    public static Boolean FINISH_INDEX = false;

    @Override
    public void onCreate() {
        super.onCreate();
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true).build();
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .diskCacheSize(50*1024*1024)
                .diskCacheFileCount(100)
                .writeDebugLogs().build();
        ImageLoader.getInstance().init(config);
        Fresco.initialize(this);
        RequestManager.getInstance().init(MyApplication.this);
        if (Build.VERSION.SDK_INT >= 24) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }
}