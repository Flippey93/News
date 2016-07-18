package com.flippey.news.global;

import android.app.Application;
import android.os.Handler;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/18 14:06
 */
public class NewsApplication extends Application {
    public static Handler sHandler;
    @Override
    public void onCreate() {
        super.onCreate();
        sHandler = new Handler();
    }
}
