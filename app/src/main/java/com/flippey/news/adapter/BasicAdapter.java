package com.flippey.news.adapter;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/21 9:25
 */
public abstract class BasicAdapter<T> extends BaseAdapter {
    public List<T> mDatas;
    public Context mContext;

    public BasicAdapter(Context context, List<T> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
