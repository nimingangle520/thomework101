package com.shushan.thomework101.tutorship;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.shushan.thomework101.R;


public class MenuPopupWindow extends PopupWindow implements View.OnClickListener{

    private View view;
    private OnItemClickListener mListener;

    public MenuPopupWindow(Activity context, OnItemClickListener mListener){
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.layout_popup_menu, null);
        this.mListener = mListener;
        this.setContentView(view);
        //this.setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
        //必须指定宽度
        this.setWidth((int) context.getResources().getDimension(R.dimen.x60));
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(false);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(0x00000000));

        view.findViewById(R.id.ll_cancel).setOnClickListener(this);
        view.findViewById(R.id.ll_news).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.ll_cancel:
                 mListener.onItemClick(this, 1);
                 break;
             case R.id.ll_news:
                 mListener.onItemClick(this, 2);
                 break;
         }
    }


    public interface  OnItemClickListener{
          void onItemClick(PopupWindow popupWindow, int position);
    }

}
