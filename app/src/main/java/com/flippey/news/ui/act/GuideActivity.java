package com.flippey.news.ui.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flippey.news.R;
import com.flippey.news.ui.view.TransformPage;
import com.flippey.news.utils.DensityUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/18 14:15
 */
public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.guide_ll)
    LinearLayout mGuideLl;
    @BindView(R.id.guide_btn)
    Button mGuideBtn;
    @BindView(R.id.guide_vp)
    ViewPager mGudieVp;
    @BindView(R.id.guide_iv_red)
    ImageView mGuideIvRed;
    private int[] mImgRs = new int[]{R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3};
    private ArrayList<ImageView> mImageViews = new ArrayList<>();
    private int mWidth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        for (int i = 0; i < mImgRs.length; i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setBackgroundResource(mImgRs[i]);
            mImageViews.add(imageView);
        }
        for (int i = 0; i < mImgRs.length; i++) {
            ImageView imageView = new ImageView(GuideActivity.this);
            imageView.setImageResource(R.drawable.dot_normal);
            int width = DensityUtil.dip2px(GuideActivity.this, 10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, width);
            if (i != 0) {
                params.leftMargin = width;
            }
            imageView.setLayoutParams(params);
            mGuideLl.addView(imageView);
        }
        pagerAdapter pagerAdapter = new pagerAdapter();
        mGudieVp.setAdapter(pagerAdapter);
        mGudieVp.addOnPageChangeListener(this);
        mGudieVp.setPageTransformer(true, new TransformPage());
       mGuideIvRed.postDelayed(new Runnable() {
            @Override
            public void run() {
                //计算小圆点之间的距离
                mWidth = mGuideLl.getChildAt(1).getLeft() - mGuideLl.getChildAt(0).getLeft();
            }
        }, 10);
        mGuideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        float v = mWidth * (position + positionOffset);
        mGuideIvRed.setTranslationX(v);
    }

    @Override
    public void onPageSelected(int position) {
        if (position == mImgRs.length - 1) {
            mGuideBtn.setVisibility(View.VISIBLE);
        } else {
            mGuideBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public class pagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mImgRs.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = mImageViews.get(position);
            container.addView(imageView);
            //mGudieVp.addChildView(position, imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //mGudieVp.removeChildView(position, mImageViews.get(position));
            container.removeView(mImageViews.get(position));
        }
    }


}
