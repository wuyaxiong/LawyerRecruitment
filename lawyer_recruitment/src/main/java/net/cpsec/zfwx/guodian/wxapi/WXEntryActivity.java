package net.cpsec.zfwx.guodian.wxapi;

import android.os.Bundle;

import com.umeng.weixin.callback.WXCallbackActivity;

import net.cpsec.zfwx.guodian.R;

public class WXEntryActivity extends WXCallbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
    }
}
