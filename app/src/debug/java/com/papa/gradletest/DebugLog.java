package com.papa.gradletest;

import android.util.Log;

/**
 * Created by Papa on 2020/12/1
 */

public class DebugLog {
    public static final String TAG = "GradleTest";
    public static boolean DEBUG;

    public DebugLog() {
    }

    public static void setDebuggable(boolean debuggable) {
        DEBUG = debuggable;
    }

    public static void d(String msg) {
        if (DEBUG && msg != null) {
            Log.d(TAG, msg);
        }

    }

    public static void d(String msg, Throwable tr) {
        if (DEBUG && msg != null) {
            Log.d(TAG, msg, tr);
        }

    }

    public static int d(String tag, String msg) {
        return DEBUG && msg != null ? Log.d(tag, msg) : 0;
    }

    public static int d(String tag, String msg, Throwable tr) {
        return DEBUG && msg != null ? Log.d(tag, msg, tr) : 0;
    }

    public static void e(final Throwable e) {
        if (DEBUG && e != null) {
            Log.e(TAG, "msg=" + e.getMessage());
            e.printStackTrace();
        }

    }

    /** @deprecated */
    @Deprecated
    public static void e(String msg) {
        if (DEBUG && msg != null) {
            Log.e("Facemoji", msg);
        }

    }

    public static int e(String tag, String msg) {
        return DEBUG && msg != null ? Log.e(tag, msg) : 0;
    }

    public static void e(String msg, Throwable tr) {
        if (DEBUG && msg != null) {
            Log.e("Facemoji", msg, tr);
        }

    }

    public static int e(String tag, String msg, Throwable tr) {
        return DEBUG && msg != null ? Log.e(tag, msg, tr) : 0;
    }

    public static void v(String msg) {
        if (DEBUG && msg != null) {
            Log.v("Facemoji", msg);
        }

    }

    public static int v(String tag, String msg) {
        return DEBUG && msg != null ? Log.v(tag, msg) : 0;
    }
}

