package net.cpsec.zfwx.guodian.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限检测申请工具类
 * author nixiong
 * Created by ad on 2017/2/31.
 */

public class PermissionsChecker {
    private final Context mContext;
    private List<String> permissions;

    public PermissionsChecker(Context context) {
        mContext = context;
        permissions = new ArrayList<>();
    }

    // 判断权限集合
    public void lacksPermissions(String[] permission_) {
        for (int i = 0;i < permission_.length;i++) {
            if (lacksPermission(permission_[i])) {
                permissions.add(permission_[i]);
            }
        }
        if(permissions.size() > 0){
            String[] strs = new String[permissions.size()];
            for(int i = 0;i < permissions.size();i++){
               strs[i] = permissions.get(i);
            }
            ActivityCompat.requestPermissions((Activity) mContext,
                    strs,
                    2);
        }
    }

    // 判断是否缺少权限
    public boolean lacksPermission(String permission) {
        return ContextCompat.checkSelfPermission(mContext, permission) ==
                PackageManager.PERMISSION_DENIED;
    }
}
