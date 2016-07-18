package com.flippey.news.ui.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.HashMap;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/18 18:30
 */
public class GuideViewPager extends ViewPager {
    private HashMap<Integer, ImageView> mHashMap = new HashMap<>();
    private ImageView mLeftView;
    private ImageView mRightView;
    private static final float MIN_SCALE = 0.75f;//用来计算right页面的缩放取值范围

    public GuideViewPager(Context context) {
        super(context);
    }

    public GuideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addChildView(int position, ImageView imageView) {
        mHashMap.put(position, imageView);
    }

    public void removeChildView(int position, ImageView imageView) {
        mHashMap.remove(position);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        mLeftView = mHashMap.get(position);
        mRightView = mHashMap.get(position + 1);
        startAnimation(mLeftView, mRightView, position, offset, offsetPixels);
    }

    private void startAnimation(ImageView leftView, ImageView rightView, int position, float offset, int offsetPixels) {
        if (rightView != null) {
            float i = getWidth() - offsetPixels;
            rightView.setTranslationX(-i);
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * Math.abs(offset);
            rightView.setScaleX(scaleFactor);
            rightView.setScaleY(scaleFactor);
        }
        if (leftView != null) {
            leftView.bringToFront();
        }
    }
}
