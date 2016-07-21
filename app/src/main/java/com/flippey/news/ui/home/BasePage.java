package com.flippey.news.ui.home;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.flippey.news.R;
import com.flippey.news.ui.act.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/19 10:16
 */
public abstract class BasePage {
    public  SlidingMenu mSlidingMenu;
    private View mView;
    public Context mContext;
    public boolean isLoad = false;
    public TextView mTxt_title;

    public BasePage(Context context) {
        this.mContext = context;
        mSlidingMenu = ((MainActivity) mContext).getSlidingMenu();
        mView = initView(context);
    }
    public View getView() {
        return mView;
    }

    public abstract View initView(Context context);

    public abstract void initData();

    //初始化标题
    public void initTitleBar(View view) {
        Button btn_left = (Button) view.findViewById(R.id.btn_left);
        btn_left.setVisibility(View.GONE);
        mTxt_title = (TextView) view.findViewById(R.id.txt_title);
        ImageButton imgbtn_right = (ImageButton) view.findViewById(R.id.imgbtn_right);
        imgbtn_right.setVisibility(View.GONE);
        //这是左边图片的点击时间
        ImageButton imgbtn_left = (ImageButton) view.findViewById(R.id.imgbtn_left);
        imgbtn_left.setImageResource(R.drawable.img_menu);
        imgbtn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.toggle();
            }
        });
    }
}
