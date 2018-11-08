package com.shushan.thomework101.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shushan.thomework101.R;
import com.shushan.thomework101.third.action.MyPath;
import com.shushan.thomework101.third.doodle.ActionTypeEnum;
import com.shushan.thomework101.third.doodle.DoodleView;
import com.shushan.thomework101.third.doodle.SupportActionType;
import com.shushan.thomework101.third.doodle.Transaction;

import java.util.ArrayList;

import static android.os.Looper.getMainLooper;

public class HomeCheckViewpagerAdapter extends PagerAdapter {

        private ArrayList<String> totalPathList;
        private Context context;
        private ImageView iv_start_topic_item;
        private DoodleView doodleView;
        private String sessionId;
        private boolean isOpen;

        public HomeCheckViewpagerAdapter(String sessionId,boolean isOpen,ArrayList<String> totalPathList, Context context) {
            this.totalPathList = totalPathList;
            this.context = context;
            this.sessionId=sessionId;
            this.isOpen=isOpen;
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

            View view = LayoutInflater.from(context).inflate(R.layout.homework_check_viewpager_item, null);
            iv_start_topic_item = (ImageView) view.findViewById(R.id.iv_start_topic_item);
            doodleView = view.findViewById(R.id.doodle_view);
            if(isOpen){
                doodleView.setEnableView(true);
            }else{
                doodleView.setEnableView(true);
            }

            initDoodleView(null);

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

    private void initDoodleView(String account){

        Toast.makeText(context, "init doodle success", Toast.LENGTH_SHORT).show();
        //add support ActionType
        SupportActionType.getInstance().addSupportActionType(ActionTypeEnum.Path.getValue(), MyPath.class);
        if (isOpen) {
            doodleView.init(sessionId, account, DoodleView.Mode.BOTH, Color.WHITE, Color.BLACK, context, new DoodleView.FlipListener() {
                @Override
                public void onFlipPage(Transaction transaction) {

                }
            });
        } else {
            doodleView.init(sessionId, account, DoodleView.Mode.BOTH,Color.WHITE, Color.BLACK, context, new DoodleView.FlipListener() {
                @Override
                public void onFlipPage(Transaction transaction){

                }
            });
        }

        doodleView.setPaintSize(3);
        doodleView.setPaintType(ActionTypeEnum.Path.getValue());

        //doodleView.setBackgroundResource(R.drawable.homework);

        // adjust paint offset
        new Handler(getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Rect frame = new Rect();
                //getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
                int statusBarHeight = frame.top;
                Log.i("Doodle", "statusBarHeight =" + statusBarHeight);

                int marginTop = doodleView.getTop();
                Log.i("Doodle", "doodleView marginTop =" + marginTop);

                int marginLeft = doodleView.getLeft();
                Log.i("Doodle", "doodleView marginLeft =" + marginLeft);

                float offsetX = marginLeft;
                //float offsetY = statusBarHeight + marginTop + ScreenUtil.dip2px(220) + ScreenUtil.dip2px(40);
                float offsetY = statusBarHeight + marginTop;

                doodleView.setPaintOffset(offsetX, offsetY);
                Log.i("Doodle", "client1 offsetX = " + offsetX + ", offsetY = " + offsetY);
            }
        }, 50);
    }
    }

