package com.flippey.news.ui.news;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.flippey.news.bean.NewItemBean;
import com.flippey.news.ui.home.BasePage;
import com.flippey.news.utils.GsonTools;
import com.flippey.news.utils.HMAPI;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by xx on 2016/7/21.
 */
public class NewItemPage extends BasePage {

    private  String mUrl;//对应新闻条目界面的url地址
    private ListView mLv;

    public NewItemPage(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public View initView(Context context) {
        mLv = new ListView(mContext);
        return mLv;
    }

    @Override
    public void initData() {
        //获取数据，解析并展示
        getNetData();
    }

    private void getNetData() {
        Request request = new Request.Builder().url(HMAPI.BASE_URL+mUrl).build();
        OkHttpClient okhttpClient = new OkHttpClient();
        Call call = okhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                System.out.println("获取数据失败");
            }

            @Override
            public void onResponse(Response response) throws IOException {
                System.out.println("获取数据成功");
                String json = response.body().string();
                parseJson(json);
            }
        });
    }

    private void parseJson(String json) {
        NewItemBean newItemBean = GsonTools.changeGsonToBean(json, NewItemBean.class);
        System.out.println(newItemBean.toString());
    }
}
