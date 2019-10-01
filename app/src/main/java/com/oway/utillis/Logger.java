package com.oway.utillis;

import android.util.Log;

public final class Logger {

    private static final String TAG = "OWAI ";
    public static boolean isEnable = true;

    private Logger() {
    }

    public static void d(String msg) {
        if (isEnable) Log.d(TAG, TAG.concat(" -> ").concat(msg));
    }

    public static void d(String tag, String msg) {
        if (isEnable) Log.d(tag, tag.concat(" -> ").concat(msg));
    }

    public static void e(String msg) {
        if (isEnable) Log.e(TAG, TAG.concat(" -> ").concat(msg));
    }

    public static void e(String tag, String msg) {
        if (isEnable) Log.e(tag, tag.concat(" -> ").concat(msg));

    }

    public static void i(String msg) {
        if (isEnable) Log.i(TAG, TAG.concat(" -> ").concat(msg));
    }

    public static void i(String tag, String msg) {
        if (isEnable) Log.i(tag, tag.concat(" -> ").concat(msg));

    }

    public static void w(String msg) {
        if (isEnable) Log.w(TAG, TAG.concat(" -> ").concat(msg));
    }

    public static void w(String tag, String msg) {
        if (isEnable) Log.w(tag, tag.concat(" -> ").concat(msg));

    }

    public static void v(String msg) {
        if (isEnable) Log.v(TAG, TAG.concat(" -> ").concat(msg));
    }

    public static void v(String tag, String msg) {
        if (isEnable) Log.v(tag, tag.concat(" -> ").concat(msg));
    }

}
