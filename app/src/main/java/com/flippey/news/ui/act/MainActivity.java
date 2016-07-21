package com.flippey.news.ui.act;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.flippey.news.R;
import com.flippey.news.ui.fragment.HomeFragment;
import com.flippey.news.ui.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends SlidingFragmentActivity {

    @BindView(R.id.main_fl_home)
    FrameLayout mMainFl;
    public SlidingMenu mSlidingMenu;
    private MenuFragment mMenuFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        //设置左边菜单界面
        setBehindContentView(R.layout.main_menu);
        //setBehindContentView(R.layout.activity_menu);
        //设置显示模式
        //得到侧滑菜单的对象
        mSlidingMenu = getSlidingMenu();
        /**
         * SlidingMenu.LEFT 显示到左边
         * SlidingMenu.RIGHT 显示到右边
         * SlidingMenu.LEFT_RIGHT 左右都显示
         */
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        //设置触摸模式
        /**
         * SlidingMenu.TOUCHMODE_FULLSCREEN);全屏模式
         * SlidingMenu.TOUCHMODE_MARGIN;边缘模式
         * SlidingMenu.TOUCHMODE_NONE 进行侧滑菜单的显示
         *
         */
        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        //设置当侧滑菜单界面显示后，主界面剩余的宽度160dp
        mSlidingMenu.setBehindOffsetRes(R.dimen.slidmenu_main_width);
        //显示阴影效果
        mSlidingMenu.setShadowDrawable(R.drawable.menu_shadow);
        mMenuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu_fl, mMenuFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fl_home, new HomeFragment(),
                "Home").commit();
    }

    public SlidingMenu getMenu() {
        return mSlidingMenu;
    }

    public MenuFragment getMenuFragment() {
        return mMenuFragment;
    }
}
