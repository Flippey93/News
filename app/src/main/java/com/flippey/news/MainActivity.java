package com.flippey.news;

import android.os.Bundle;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        //设置左边菜单界面
        setBehindContentView(R.layout.main_menu);
        //setBehindContentView(R.layout.activity_menu);
        //设置显示模式
        SlidingMenu slidingMenu = getSlidingMenu();//得到侧滑菜单的对象
        /**
         * SlidingMenu.LEFT 显示到左边
         * SlidingMenu.RIGHT 显示到右边
         * SlidingMenu.LEFT_RIGHT 左右都显示
         */
        slidingMenu.setMode(SlidingMenu.LEFT);
        //设置触摸模式
        /**
         * SlidingMenu.TOUCHMODE_FULLSCREEN);全屏模式
         * SlidingMenu.TOUCHMODE_MARGIN;边缘模式
         * SlidingMenu.TOUCHMODE_NONE 进行侧滑菜单的显示
         *
         */
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        //设置当侧滑菜单界面显示后，主界面剩余的宽度160dp
        slidingMenu.setBehindOffsetRes(R.dimen.slidmenu_main_width);
    }
}
