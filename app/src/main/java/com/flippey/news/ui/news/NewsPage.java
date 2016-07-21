package com.flippey.news.ui.news;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.flippey.news.R;
import com.flippey.news.bean.NewsCenterBean;
import com.flippey.news.ui.home.BasePage;
import com.flippey.news.ui.viewpagerindicator.TabPageIndicator;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/21 19:07
 */
public class NewsPage extends BasePage {
    private NewsCenterBean.DataBean mDataBean;
    private TabPageIndicator mIndicator;
    private ViewPager mPager;
    public int mCurrentItem; //记录当前新闻条目界面显示位置
    public NewsPage(Context context) {
        super(context);
    }

    public NewsPage(Context context, NewsCenterBean.DataBean dataBean) {
        super(context);
        mDataBean = dataBean;
    }

    @Override
    public View initView(Context context) {
        View view = View.inflate(context, R.layout.simple_tabs, null);
        mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator);
        mPager = (ViewPager) view.findViewById(R.id.pager);
        return view;
    }

    @Override
    public void initData() {
        newTitles.clear();
        newPages.clear();
        for (NewsCenterBean.DataBean.ChildrenBean childrenBean : mDataBean.getChildren()) {
            newTitles.add(childrenBean.getTitle());
            newPages.add(new NewItemPage(mContext, childrenBean.getUrl()));
        }
        NewAdapter newAdapter = new NewAdapter();
        mPager.setAdapter(newAdapter);
        mIndicator.setViewPager(mPager);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentItem = position;
                if (position == 0) {
                    mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                }else{//让侧滑菜单禁止滑动显示
                    mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                }
                //调用对应的界面,执行数据初始化
                BasePage basePage = newPages.get(position);
                if (!basePage.isLoad) {
                    basePage.initData();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //第一个默认显示
        newPages.get(0).initData();
    }
    private List<BasePage> newPages = new ArrayList<>();//新闻界面的页面对象集合
    private List<String> newTitles = new ArrayList<>();//新闻界面的指针的标题数据集合

    class NewAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return newPages.size();
        }

        //如果要让指针展示出标题必须实现这个方法
        @Override
        public CharSequence getPageTitle(int position) {
            return newTitles.get(position);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(newPages.get(position).getView());
            return newPages.get(position).getView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
