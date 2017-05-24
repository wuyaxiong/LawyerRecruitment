package net.cpsec.zfwx.lawyer_recruitment.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

public class Toast {
    public static void prompt(Context context, String content) {
        if (context != null)
            android.widget.Toast.makeText(context, content, android.widget.Toast.LENGTH_SHORT).show();
    }
}
