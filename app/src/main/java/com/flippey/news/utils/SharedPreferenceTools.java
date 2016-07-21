package com.flippey.news.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xx on 2016/7/18.
 */
public class SharedPreferenceTools {
    private static final String SP_NAME = "config";
    private static SharedPreferences mSp;

    //保存boolean值
    public static void saveBoolean(Context context, String key, boolean value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putBoolean(key, value).commit();
    }

    //获取boolean值
    public static boolean getBoolean(Context context, String key, boolean defvalue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        boolean result = mSp.getBoolean(key, defvalue);
        return result;
    }

    //保存String值
    public static void saveString(Context context, String key, String value) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        mSp.edit().putString(key, value).commit();
    }

    //获取String值
    public static String getString(Context context, String key, String defvalue) {
        if (mSp == null) {
            mSp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
        String result = mSp.getString(key, defvalue);
        return result;
    }
}
