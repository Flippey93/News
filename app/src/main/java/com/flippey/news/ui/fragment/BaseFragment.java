package com.flippey.news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flippey.news.ui.act.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;

import butterknife.ButterKnife;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/19 14:18
 */
public abstract class BaseFragment extends Fragment {

    public Context mContext;
    public SlidingMenu mSlidingMenu;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        MainActivity activity = (MainActivity) getActivity();
        mSlidingMenu = activity.getMenu();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = creatView(inflater);
        ButterKnife.bind(this, view);
        ViewUtils.inject(this,view);
        return view;
    }

    protected abstract View creatView(LayoutInflater inflater);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected abstract void initData();
}
