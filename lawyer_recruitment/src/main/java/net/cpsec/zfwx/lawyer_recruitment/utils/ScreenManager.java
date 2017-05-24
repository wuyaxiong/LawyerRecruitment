package net.cpsec.zfwx.lawyer_recruitment.utils;

import android.app.Activity;

import net.cpsec.zfwx.lawyer_recruitment.MyApplication;

import java.util.Stack;

public class ScreenManager {
    public static Stack<Activity> activityStack;
    private static ScreenManager instance;

    public static ScreenManager getScreenManager() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    public void popActivity() {
        Activity activity;
        // TODO 这里可能需要修改
        if (MyApplication.FINISH_INDEX) {
            if (activityStack.size() >= 2) {
                activity = activityStack.get(activityStack.size() - 2);
            } else {
                activity = activityStack.get(activityStack.size() - 1);
            }
        } else {
            activity = activityStack.get(activityStack.size() - 1);
        }
//        activity = activityStack.lastElement();
        if (activity != null) {
            popActivity(activity);
        }
    }

    public void popActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity = null;
        }
    }

    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public Activity getActivityByIndex(int index) {
        Activity activity = activityStack.elementAt(index);
        return activity;
    }

    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public void popAllActivityExceptOne(Class cls) {
        while (true) {
            Activity activity = currentActivity();
            if (activity == null) {
                break;
            }
            if (activity.getClass().equals(cls)) {
                break;
            }
            popActivity(activity);
        }
    }
}
