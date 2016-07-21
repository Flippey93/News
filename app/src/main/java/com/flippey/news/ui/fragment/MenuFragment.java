package com.flippey.news.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.flippey.news.R;
import com.flippey.news.adapter.BasicAdapter;
import com.flippey.news.ui.act.MainActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/19 18:56
 */
public class MenuFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    @BindView(R.id.tv_menu_classify)
    TextView mTvMenuClassify;
    @BindView(R.id.lv_menu_news_center)
    ListView mLvMenuNewsCenter;
    @BindView(R.id.lv_menu_smart_service)
    ListView mLvMenuSmartService;
    @BindView(R.id.lv_menu_govaffairs)
    ListView mLvMenuGovaffairs;
    private MenuAdapter mNewsAdapter;


    @Override
    protected View creatView(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.layout_left_menu, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    protected void initData() {

    }

    protected List<String> mNewsCenter = new ArrayList<>();

    public void initMenu(List<String> menuTitle) {
        mNewsCenter.clear();
        mNewsCenter.addAll(menuTitle);
        mNewsAdapter = new MenuAdapter(mContext, mNewsCenter);
        mLvMenuNewsCenter.setAdapter(mNewsAdapter);
        mLvMenuNewsCenter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_menu_news_center:  //新闻中心
                mNewsAdapter.setClickItemPosition(position);
                ((MainActivity) mContext).getHomeFragment().getNewsCenterPage().switchView(position);
                break;
        }
        //侧滑菜单关闭
        mSlidingMenu.toggle();
    }

    private class MenuAdapter extends BasicAdapter<String> {

        private int mClickPosition;//用来记录当前选中的条目位置

        public MenuAdapter(Context context, List<String> datas) {
            super(context, datas);
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = View.inflate(mContext,R.layout.layout_item_menu, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.tv_menu_item);
            ImageView iv = (ImageView) convertView.findViewById(R.id.iv_menu_item);
            tv.setText(mDatas.get(position));
            if (position == mClickPosition) {//当前绘制的条目位置与被点击的条目位置一致的话，就设置为选中状态
                tv.setTextColor(Color.RED);
                iv.setImageResource(R.drawable.menu_arr_select);
                convertView.setBackgroundResource(R.drawable.menu_item_bg_select);
            }else{
                tv.setTextColor(Color.WHITE);
                iv.setImageResource(R.drawable.menu_arr_normal);
                convertView.setBackgroundResource(android.R.color.transparent);
            }
            return convertView;
        }

        public void setClickItemPosition(int position) {
            this.mClickPosition = position;
            notifyDataSetChanged();
        }
    }
}
