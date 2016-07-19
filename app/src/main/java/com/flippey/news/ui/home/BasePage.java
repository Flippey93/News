package com.flippey.news.ui.home;

import android.content.Context;
import android.view.View;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/19 10:16
 */
public abstract class BasePage {
    private View mView;
    public Context mContext;
    public abstract View initView();

    public BasePage(Context context) {
        this.mContext = context;
        mView = initView();
    }
    public View getView() {
        return mView;
    }
}
