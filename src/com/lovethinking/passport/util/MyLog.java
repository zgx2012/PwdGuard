package com.lovethinking.passport.util;

import android.util.Log;

public class MyLog {
    private static final String TAG = "MyLog";
    public static boolean DEBUG = true;

    public static void w(String tag, Object msg) {
        log(tag, msg.toString(), 'w');
    }

    public static void e(String tag, Object msg) {
        log(tag, msg.toString(), 'e');
    }

    public static void d(String tag, Object msg) {
        log(tag, msg.toString(), 'd');
    }

    public static void i(String tag, Object msg) {
        log(tag, msg.toString(), 'i');
    }

    public static void v(String tag, Object msg) {
        log(tag, msg.toString(), 'v');
    }

    // =====================================================
    public static void w(Object msg) {
        log(TAG, msg.toString(), 'w');
    }

    public static void e(Object msg) {
        log(TAG, msg.toString(), 'e');
    }

    public static void d(Object msg) {
        log(TAG, msg.toString(), 'd');
    }

    public static void i(Object msg) {
        log(TAG, msg.toString(), 'i');
    }

    public static void v(Object msg) {
        log(TAG, msg.toString(), 'v');
    }

    private static void log(String tag, String msg, char level) {
        if (DEBUG) {
            if ('e' == level) {
                Log.e(tag, msg);
            } else if ('w' == level) {
                Log.w(tag, msg);
            } else if ('d' == level) {
                Log.d(tag, msg);
            } else if ('i' == level) {
                Log.i(tag, msg);
            } else {
                Log.v(tag, msg);
            }
        }
    }
}
