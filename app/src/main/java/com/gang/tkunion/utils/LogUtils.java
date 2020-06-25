package com.gang.tkunion.utils;

import android.util.Log;

/**
 * 控制log的输出
 * currentLevel值越大，输出的log数量越多
 */
public class LogUtils {

    private static final int INFO_LEVEL = 4;
    private static final int DEBUG_LEVEL = 3;
    private static final int WARNING_LEVEL = 2;
    private static final int ERROR_LEVEL = 1;
    private static int currentLevel = 4;

    public static void i(String tag, String log) {
        if (currentLevel >= INFO_LEVEL) {
            Log.i(tag, log);
        }
    }

    public static void d(String tag, String log) {
        if (currentLevel >= DEBUG_LEVEL) {
            Log.d(tag, log);
        }
    }

    public static void w(String tag, String log) {
        if (currentLevel >= WARNING_LEVEL) {
            Log.w(tag, log);
        }
    }

    public static void e(String tag, String log) {
        if (currentLevel >= ERROR_LEVEL) {
            Log.e(tag, log);
        }
    }
}