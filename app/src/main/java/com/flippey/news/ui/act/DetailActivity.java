package com.flippey.news.ui.act;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flippey.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/23 9:37
 */
public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.news_detail_wv)
    WebView mNewsDetailWv;
    @BindView(R.id.btn_left)
    Button mBtnLeft;
    @BindView(R.id.imgbtn_left)
    ImageButton mImgbtnLeft;
    @BindView(R.id.txt_title)
    TextView mTxtTitle;
    @BindView(R.id.imgbtn_text)
    ImageButton mImgbtnText;
    @BindView(R.id.imgbtn_right)
    ImageButton mImgbtnRight;
    @BindView(R.id.btn_right)
    ImageButton mBtnRight;
    @BindView(R.id.title_bar)
    LinearLayout mTitleBar;
    /*@BindView(R.id.pb_loading)
    ProgressBar mPbLoading;*/
    @BindView(R.id.ll_load_fail)
    LinearLayout mLlLoadFail;
    @BindView(R.id.loading_view)
    View mLoad;
    private WebSettings mSettings;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_detail);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mBtnLeft.setVisibility(View.GONE);
        mImgbtnLeft.setImageResource(R.drawable.back);
        mImgbtnLeft.setOnClickListener(this);
        mBtnRight.setImageResource(R.drawable.icon_share);
        mBtnRight.setOnClickListener(this);
        mImgbtnRight.setImageResource(R.drawable.icon_textsize);
        mImgbtnRight.setOnClickListener(this);

        String url = getIntent().getStringExtra("url");
        mNewsDetailWv.loadUrl(url);
        //webview 设置
        mSettings = mNewsDetailWv.getSettings();
        mNewsDetailWv.setWebViewClient(new WebViewClient(){
            //网页加载之前
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mLoad.setVisibility(View.GONE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgbtn_right: // 字体设置
                mSettings.setTextSize(WebSettings.TextSize.LARGER);
                break;
            case R.id.btn_right: //分享
                break;
            case R.id.imgbtn_left: //返回
                this.finish();
                break;
        }
    }
}
