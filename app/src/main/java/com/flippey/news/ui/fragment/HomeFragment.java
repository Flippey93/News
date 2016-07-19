package com.flippey.news.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flippey.news.R;
import com.flippey.news.ui.home.BasePage;
import com.flippey.news.ui.home.FunctionPage;
import com.flippey.news.ui.home.GoverPage;
import com.flippey.news.ui.home.NewsCenterPage;
import com.flippey.news.ui.home.SettingPage;
import com.flippey.news.ui.home.SmartServicePage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/19 10:09
 */
public class HomeFragment extends Fragment {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    //主界面的页面集合
    private List<BasePage> mHomePages;
    private Context mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
    Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建viewpager数据集合,保存对应的5个界面
        mHomePages = new ArrayList<>();
        mHomePages.add(new FunctionPage(mContext));
        mHomePages.add(new NewsCenterPage(mContext));
        mHomePages.add(new GoverPage(mContext));
        mHomePages.add(new SmartServicePage(mContext));
        mHomePages.add(new SettingPage(mContext));
    }

    class HomeAdapter extends PagerAdapter{

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
