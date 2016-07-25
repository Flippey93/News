package com.flippey.news.ui.news;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flippey.news.R;
import com.flippey.news.adapter.NewItemAdapter;
import com.flippey.news.bean.NewItemBean;
import com.flippey.news.ui.act.DetailActivity;
import com.flippey.news.ui.home.BasePage;
import com.flippey.news.ui.view.RollViewpager;
import com.flippey.news.utils.DensityUtil;
import com.flippey.news.utils.GsonTools;
import com.flippey.news.utils.HMAPI;
import com.flippey.news.utils.SharedPreferenceTools;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import PullToRefresh.PullToRefreshBase;
import PullToRefresh.PullToRefreshListView;

/**
 * Created by xx on 2016/7/21.
 */
public class NewItemPage extends BasePage {

    private String mUrl;//对应新闻条目界面的url地址
    private PullToRefreshListView mLv;
    private View mTopView;
    private boolean isFresh; //用来记录当前是下拉刷新还是加载更多
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isLoad = true; //设置为已加载数据
            if (mNewItemAdapter == null) {
                mNewItemAdapter = new NewItemAdapter(mContext, mNewsData);
                mLv.getRefreshableView().setAdapter(mNewItemAdapter);
            } else {
                mNewItemAdapter.notifyDataSetChanged();
            }
            if (isFresh) {
                initDots(imgLists.size());
                if (mRollViewpager != null) {
                    //先将上次的handler取消
                    mRollViewpager.stop();
                }
                mRollViewpager = new RollViewpager(mContext, dosts);
                mRollViewpager.setImgSize(imgLists);
                mRollViewpager.setTitle(mTopTitle, topNewsTitles);
                mRollViewpager.setOnRollClickListener(new RollViewpager.onRollClickListener() {
                    @Override
                    public void onRollClick() {
                        Toast.makeText(mContext, "跳转页面", Toast.LENGTH_SHORT).show();
                    }
                });
                mRollViewpager.start();
                //将轮播图添加到容器中去
                mTopLl.removeAllViews();
                mTopLl.addView(mRollViewpager);

            }
            //只有当头布局为0时才添加头布局,避免重复添加
            int headerViewsCount = mLv.getRefreshableView().getHeaderViewsCount();
            if (true) {
                if (headerViewsCount == 0) { //当头布局个数为0时才添加头布局
                    mLv.getRefreshableView().addHeaderView(mTopView);
                }
            }
            mLv.onPullUpRefreshComplete();
            mLv.onPullDownRefreshComplete();
            //设置最新刷新时间
            String currentTime = getCurrentTime();
            mLv.setLastUpdatedLabel(currentTime);
        }
    };

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    private NewItemAdapter mNewItemAdapter;
    private RollViewpager mRollViewpager;
    private TextView mTopTitle;
    private LinearLayout mDots;
    private LinearLayout mTopLl;

    public NewItemPage(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    private String mMoreUrl; //下拉刷新与加载更多的接口数据

    @Override
    public View initView(Context context) {
        View view = View.inflate(context, R.layout.frag_item_news, null);
        mLv = (PullToRefreshListView) view.findViewById(R.id.lv_item_news);
        mLv.setPullRefreshEnabled(true); //设置下拉刷新可用
        mLv.setPullLoadEnabled(true);  //设置上拉加载可用
        mLv.setScrollLoadEnabled(false); //设置滑动到最后一行自动加载数据
        mLv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (TextUtils.isEmpty(mMoreUrl)) {
                    Toast.makeText(mContext, "没有最新数据了", Toast.LENGTH_SHORT).show();
                    mLv.onPullDownRefreshComplete();
                } else {
                    //设置新的接口
                    getNetData(mMoreUrl, true);
                }
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                if (TextUtils.isEmpty(mMoreUrl)) {
                    Toast.makeText(mContext, "没有更多数据", Toast.LENGTH_SHORT).show();
                    mLv.onPullUpRefreshComplete();
                } else {
                    getNetData(mMoreUrl, false);
                }
            }
        });

        //设置条目点击事件
        mLv.getRefreshableView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击条目后标记为已读
                NewItemBean.DataBean.NewsBean newsBean = (NewItemBean.DataBean.NewsBean) parent
                        .getItemAtPosition(position);
                if ( !newsBean.isRead) {
                    newsBean.isRead = true;
                    mNewItemAdapter.notifyDataSetChanged();
                }
                //TODO 进入详情页
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("url", newsBean.getUrl());
                mContext.startActivity(intent);
            }
        });
        mTopView = View.inflate(context, R.layout.layout_roll_view, null);
        mTopLl = (LinearLayout) mTopView.findViewById(R.id.top_news_viewpager);
        mTopTitle = (TextView) mTopView.findViewById(R.id.top_news_title);
        mDots = (LinearLayout) mTopView.findViewById(R.id.dots_ll);
        //view.setBackgroundColor(Color.BLUE);

        //设置一下上次的刷新时间
        String updateTime = SharedPreferenceTools.getString(mContext, "updateTime", "");
        mLv.setLastUpdatedLabel(updateTime);
        return view;
    }

    @Override
    public void initData() {
        //先从本地拿数据
        String json = SharedPreferenceTools.getString(mContext, HMAPI.BASE_URL + mUrl, "");
        if (!TextUtils.isEmpty(json)) {
            // parseJson(json, isFresh);
            parseJson(json, true);
        }
        //获取数据，解析并展示
        getNetData(mUrl, true);
    }

    private void getNetData(String moreUrl, final boolean isFresh) {
        Request request = new Request.Builder().url(HMAPI.BASE_URL + moreUrl).build();
        OkHttpClient okhttpClient = new OkHttpClient();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("获取数据失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                // System.out.println("获取数据成功");
                String json = response.body().string();
                parseJson(json, isFresh);
            }
        });
    }

    private List<NewItemBean.DataBean.NewsBean> mNewsData = new ArrayList<>(); //新闻列表数据库
    private List<String> topNewsTitles = new ArrayList<>(); //热门新闻的标题
    private List<String> imgLists = new ArrayList<>(); //热门新闻图片url
    private List<ImageView> dosts = new ArrayList<>(); //小圆点集合

    private void parseJson(String json, boolean isFresh) {
        NewItemBean newItemBean = GsonTools.changeGsonToBean(json, NewItemBean.class);
        this.isFresh = isFresh;
        if (isFresh) {
            mNewsData.clear();
            mNewsData.addAll(newItemBean.getData().getNews());
            //热门新闻跟图片url数据集合
            topNewsTitles.clear();
            imgLists.clear();
            for (NewItemBean.DataBean.TopnewsBean topnewsBean : newItemBean.getData().getTopnews
                    ()) {
                topNewsTitles.add(topnewsBean.getTitle());
                imgLists.add(topnewsBean.getTopimage());
            }
        } else {
            //加载更多,直接接在当前数据的后面
            mNewsData.addAll(newItemBean.getData().getNews());
        }
        mMoreUrl = newItemBean.getData().getMore();
        //System.out.println(newItemBean.toString());
        SharedPreferenceTools.saveString(mContext, HMAPI.BASE_URL + mUrl, json);
        mHandler.sendEmptyMessage(0);
    }

    //动态创建小圆点
    private void initDots(int size) {
        dosts.clear();
        mDots.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(mContext);
            int dPx = DensityUtil.dip2px(mContext, 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(dPx, dPx);
            if (i == 0) {
                imageView.setBackgroundResource(R.drawable.dot_focus);
            } else {
                imageView.setBackgroundResource(R.drawable.dot_normal);
                params.leftMargin = dPx;
            }
            imageView.setLayoutParams(params);
            dosts.add(imageView);
            mDots.addView(imageView);
        }
    }
}
