package com.flippey.news.ui.news;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.flippey.news.ui.home.BasePage;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/21 19:07
 */
public class TopicPage extends BasePage {
    public TopicPage(Context context) {
        super(context);
    }

    @Override
    public View initView(Context context) {
        TextView textView = new TextView(context);
        textView.setText("topic");
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {

    }
}
