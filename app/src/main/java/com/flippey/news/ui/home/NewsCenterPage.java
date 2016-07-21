package com.flippey.news.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.flippey.news.bean.NewsCenterBean;
import com.flippey.news.ui.act.MainActivity;
import com.flippey.news.utils.GsonTools;
import com.flippey.news.utils.HMAPI;
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

    public NewsCenterPage(Context context) {
        super(context);
    }
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    MainActivity mainActivity = (MainActivity) mContext;
                    mainActivity.getMenuFragment().initMenu(mMenuTitle);
                    break;
            }
        }
    };
    private List<String> mMenuTitle = new ArrayList<>();

    @Override
    public View initView(Context context) {
        TextView textView = new TextView(context);
        textView.setText("newscenterpage");
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
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
                parseJson(json);
            }
        });
    }

    private void parseJson(String json) {
        NewsCenterBean newsCenterBean = GsonTools.changeGsonToBean(json, NewsCenterBean.class);
       // System.out.println(newsCenterBean.getRetcode()+"....");
        mMenuTitle.clear();
        for (NewsCenterBean.DataBean dataBean : newsCenterBean.getData()) {
            mMenuTitle.add(dataBean.getTitle());
        }
        mHandler.sendEmptyMessage(0);

    }

}
