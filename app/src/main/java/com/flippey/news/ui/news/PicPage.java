package com.flippey.news.ui.news;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.flippey.news.R;
import com.flippey.news.adapter.PicAdapter;
import com.flippey.news.bean.PicBean;
import com.flippey.news.ui.home.BasePage;
import com.flippey.news.utils.GsonTools;
import com.flippey.news.utils.HMAPI;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/21 19:07
 */
public class PicPage extends BasePage {

    private ListView mLv;
    private GridView mGv;
    private ArrayList<PicBean.DataBean.NewsBean> mData = new ArrayList<>();
    public boolean isGrid;
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (mPicAdapter == null) {
                mPicAdapter = new PicAdapter(mData, mContext);
                mLv.setAdapter(mPicAdapter);
            } else {
                mPicAdapter.notifyDataSetChanged();
            }
        }
    };
    private PicAdapter mPicAdapter;

    public PicPage(Context context) {
        super(context);
    }
    @Override
    public View initView(Context context) {
        View view = View.inflate(mContext, R.layout.photo, null);
        mLv = (ListView) view.findViewById(R.id.lv_photo_list);
        mGv = (GridView) view.findViewById(R.id.gv_photo_grid);
        return view;
    }

    @Override
    public void initData() {
        getPicNetData();
    }

    private void getPicNetData() {
        Request request = new Request.Builder().url(HMAPI.PHOTO_URL).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                String json = response.body().string();
                System.out.println(json);
                parseJson(json);
            }
        });
    }

    private void parseJson(String json) {
        PicBean picBean = GsonTools.changeGsonToBean(json, PicBean.class);
        mData.clear();
        for (PicBean.DataBean.NewsBean newsBean : picBean.getData().getNews()) {
            mData.add(newsBean);
        }
        mHandler.sendEmptyMessage(0);

    }

    public void changeView() {
        if (isGrid) {
            mLv.setVisibility(View.GONE);
            mGv.setVisibility(View.VISIBLE);
            if (mGv.getAdapter() == null) {
                mGv.setAdapter(mPicAdapter);
            }
        } else {
            mLv.setVisibility(View.VISIBLE);
            mGv.setVisibility(View.GONE);
            if (mLv.getAdapter() == null) {
                mLv.setAdapter(mPicAdapter);
            }
        }
    }
}
