package com.flippey.news.ui.fragment;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.flippey.news.R;
import com.flippey.news.ui.home.BasePage;
import com.flippey.news.ui.home.FunctionPage;
import com.flippey.news.ui.home.GoverPage;
import com.flippey.news.ui.home.NewsCenterPage;
import com.flippey.news.ui.home.SettingPage;
import com.flippey.news.ui.home.SmartServicePage;
import com.flippey.news.ui.view.NoScrollViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnRadioGroupCheckedChange;

import java.util.ArrayList;
import java.util.List;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/19 10:09
 */
public class HomeFragment extends BaseFragment implements ViewPager.OnPageChangeListener {
    @ViewInject(R.id.view_pager)
    NoScrollViewPager mViewPager;
    @ViewInject(R.id.main_radio)
    RadioGroup mMainRadio;
    //主界面的页面集合
    private List<BasePage> mHomePages;


    @Override
    protected View creatView(LayoutInflater inflater) {
        return inflater.inflate(R.layout.frag_home,null);
    }

    @Override
    protected void initData() {
        //创建viewpager数据集合,保存对应的5个界面
        mHomePages = new ArrayList<>();
        mHomePages.add(new FunctionPage(mContext));
        mHomePages.add(new NewsCenterPage(mContext));
        mHomePages.add(new GoverPage(mContext));
        mHomePages.add(new SmartServicePage(mContext));
        mHomePages.add(new SettingPage(mContext));
        HomeAdapter homeAdapter = new HomeAdapter();
        mViewPager.setAdapter(homeAdapter);
        mMainRadio.check(R.id.rb_function);
        mViewPager.addOnPageChangeListener(this);
    }
    @OnRadioGroupCheckedChange(R.id.main_radio)
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_function:   //首页  禁止侧滑
                mViewPager.setCurrentItem(0, false);
                mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                break;
            case R.id.rb_gov_affairs:
                mViewPager.setCurrentItem(2, false);
                mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_news_center:   //新闻中心
                mViewPager.setCurrentItem(1, false);
                mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
            case R.id.rb_setting:    //设置中心禁止侧滑
                mViewPager.setCurrentItem(4, false);
                mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
                break;
            case R.id.rb_smart_service:
                mViewPager.setCurrentItem(3, false);
                mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        BasePage basePage = mHomePages.get(position);
        basePage.initData();
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    class HomeAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mHomePages.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mHomePages.get(position).getView();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

}
