package com.flippey.news.ui.view;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/18 20:19
 */
public class TransformPage implements ViewPager.PageTransformer{
    private float maxRadio = 25f;

    @Override
    public void transformPage(View page, float position) {
        int width = page.getWidth();
        //System.out.println(width+"..............");
        if (position < -1) {
            page.setRotation(0);
        }else if(position <=0){
            float rotate = position * maxRadio;
            page.setRotation(rotate);
            page.setPivotX(width / 2);
            page.setPivotY(page.getHeight());
        } else if (position <=1){
            float rotate = position * maxRadio;
            page.setRotation(rotate);
            page.setPivotX(width / 2);
            page.setPivotY(page.getHeight());
        }else {
            page.setRotation(0);
        }
    }
}
