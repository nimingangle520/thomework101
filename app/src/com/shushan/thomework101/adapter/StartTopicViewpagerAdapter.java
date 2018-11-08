package com.shushan.thomework101.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shushan.thomework101.R;
import com.shushan.thomework101.third.doodle.DoodleView;

import java.util.ArrayList;

public class StartTopicViewpagerAdapter extends PagerAdapter{

    private ArrayList<String> totalPathList;
    private Context context;
    private ImageView iv_start_topic_item;
    private DoodleView doodleView;

    public StartTopicViewpagerAdapter(ArrayList<String> totalPathList, Context context) {
        this.totalPathList = totalPathList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return totalPathList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.start_topic_viewpager_item, null);
        iv_start_topic_item = (ImageView) view.findViewById(R.id.iv_start_topic_item);
        Glide.with(context).load(totalPathList.get(position)).into(iv_start_topic_item);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    /**
     * 释放图片资源的方法
     *
     * @param imageView
     */
    public void releaseImageViewResouce(ImageView imageView) {
        if (imageView == null) return;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && !bitmap.isRecycled()) {
                //bitmap.recycle();
                bitmap = null;
            }
        }
        System.gc();
    }

}
