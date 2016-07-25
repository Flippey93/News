package com.flippey.news.ui.home;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.flippey.news.R;
import com.flippey.news.bean.NewsCenterBean;
import com.flippey.news.ui.act.MainActivity;
import com.flippey.news.ui.news.ActionPage;
import com.flippey.news.ui.news.NewsPage;
import com.flippey.news.ui.news.PicPage;
import com.flippey.news.ui.news.TopicPage;
import com.flippey.news.utils.GsonTools;
import com.flippey.news.utils.HMAPI;
import com.flippey.news.utils.SharedPreferenceTools;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/19 10:12
 */
public class NewsCenterPage extends BasePage {

    private FrameLayout mFrameLayout;
    private NewsCenterBean mNewsCenterBean;

    public NewsCenterPage(Context context) {
        super(context);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mNewsPage.add(new NewsPage(mContext, mNewsCenterBean.getData().get(0)));
                    mNewsPage.add(new TopicPage(mContext));
                    mNewsPage.add(new PicPage(mContext));
                    mNewsPage.add(new ActionPage(mContext));
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMenuFragment().initMenu(mMenuTitle);
                    switchView(0);
                    mTxt_title.setText(mMenuTitle.get(0));
                    break;
            }
        }
    };
    private List<String> mMenuTitle = new ArrayList<>();
    private List<BasePage> mNewsPage = new ArrayList<>();
    private int mCurrentIndex;

    @Override
    public View initView(Context context) {
        View view = View.inflate(context, R.layout.news_center_frame, null);
        mFrameLayout = (FrameLayout) view.findViewById(R.id.news_center_fl);
        initTitleBar(view);
        return view;
    }

    @Override
    public void initData() {
        //System.out.println("加载数据.....");
        String json = SharedPreferenceTools.getString(mContext, HMAPI.NEW_CENTER, "");
        if (!TextUtils.isEmpty(json)) {
            parseJson(json);
        }
        getData();
    }

    private void getData() {
        Request request = new Request.Builder().url(HMAPI.NEW_CENTER).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                // System.out.println("服务器获取数据失败" + e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                // System.out.println("获取成功");
                String json = response.body().string();
                SharedPreferenceTools.saveString(mContext, HMAPI.NEW_CENTER, json);
                parseJson(json);
            }
        });
    }

    private void parseJson(String json) {
        isLoad = true;
        mNewsCenterBean = GsonTools.changeGsonToBean(json, NewsCenterBean.class);
        // System.out.println(newsCenterBean.getRetcode()+"....");
        mMenuTitle.clear();
        for (NewsCenterBean.DataBean dataBean : mNewsCenterBean.getData()) {
            mMenuTitle.add(dataBean.getTitle());
        }

        mHandler.sendEmptyMessage(0);
    }

    public void switchView(int position) {
        mCurrentIndex = position;
        final BasePage basePage = mNewsPage.get(position);
        switch (position) {
            case 0:
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(basePage.getView());
                break;
            case 1:
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(basePage.getView());
                break;
            case 2:
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(basePage.getView());
                mImgbtnRi.setVisibility(View.VISIBLE);
                mImgbtnRi.setImageResource(R.drawable.icon_pic_grid_type);
                mImgbtnRi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PicPage picPage = (PicPage) basePage;
                        if (picPage.isGrid) {
                            picPage.isGrid = false;
                            picPage.changeView();
                            mImgbtnRi.setImageResource(R.drawable.icon_pic_list_type);
                        } else {
                            picPage.isGrid = true;
                            picPage.changeView();
                            mImgbtnRi.setImageResource(R.drawable.icon_pic_grid_type);
                        }
                    }
                });
                break;
            case 3:
                mFrameLayout.removeAllViews();
                mFrameLayout.addView(basePage.getView());
                break;
        }
        mTxt_title.setText(mMenuTitle.get(position));
        if (!basePage.isLoad) {
            basePage.initData();
        }
    }

    public void onResume() {
        super.onResume();
        if (mNewsPage != null && mNewsPage.size() > 0) {
            mNewsPage.get(mCurrentIndex).onResume();
        }
    }
}
