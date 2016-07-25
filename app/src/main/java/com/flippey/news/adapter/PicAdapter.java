package com.flippey.news.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.flippey.news.R;
import com.flippey.news.bean.PicBean;

import java.util.ArrayList;

/**
 * @ Author      Flippey
 * @ Creat Time  2016/7/25 19:37
 */
public class PicAdapter extends BaseAdapter {
    private  Context mContext;
    private  ArrayList<PicBean.DataBean.NewsBean> mData;

    public PicAdapter(ArrayList<PicBean.DataBean.NewsBean> data, Context context) {
        this.mData = data;
        this.mContext = context;

    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.photo_item, null);
            holder.mTextView = (TextView) convertView.findViewById(R.id.tv_photoitem_title);
            holder.mImageView = (ImageView) convertView.findViewById(R.id.iv_photoitem_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText(mData.get(position).getTitle());
        Glide.with(mContext.getApplicationContext()).load(mData.get(position).getListimage())
                .into(holder.mImageView);
        return convertView;
    }

    static class ViewHolder{
        ImageView mImageView;
        TextView mTextView;
    }
}
