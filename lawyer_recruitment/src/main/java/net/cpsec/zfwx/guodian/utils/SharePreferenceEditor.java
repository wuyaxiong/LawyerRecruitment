package net.cpsec.zfwx.guodian.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferenceEditor {
    private static SharePreferenceEditor sharePreferenceEditor;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharePreferenceEditor(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("BrainHealth", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
    }

    public static synchronized SharePreferenceEditor newInstance(Context context) {
        if (sharePreferenceEditor == null) {
            synchronized (SharePreferenceEditor.class) {
                if (sharePreferenceEditor == null) {
                    sharePreferenceEditor = new SharePreferenceEditor(context);
                }
            }
        }
        return sharePreferenceEditor;
    }

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public SharedPreferences.Editor getEditor() {
        return editor;
    }
}
