package com.shushan.thomework101.wallet;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shushan.thomework101.R;


public class BillFilterPopup extends PopupWindow implements View.OnClickListener {
    private Context context;
    private Animation animationIn, animationOut;
    private ConstraintLayout cl_bill_filter;
    private final TextView tv_cancel;
    private final TextView tv_total;
    private final TextView tv_homework_check;
    private final TextView tv_homework_tutorship;
    private final TextView tv_recharge;
    private final Button tv_ensure;
    private boolean isDismiss=false;
    private int label=4;//1检查2辅导3tixian4全部
    private OnItemClickListener onItemClickListener;

    public BillFilterPopup(Context context) {
        super(context);
        this.context=context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.bill_filter, null);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setBackgroundDrawable(new ColorDrawable());
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        this.update();
        this.setBackgroundDrawable(new ColorDrawable());
        this.setContentView(inflate);
        animationIn = AnimationUtils.loadAnimation(context, R.anim.pop_push_up_in);
        animationOut = AnimationUtils.loadAnimation(context, R.anim.pop_push_down_out);
        cl_bill_filter=inflate.findViewById(R.id.cl_bill_filter);
        tv_cancel = inflate.findViewById(R.id.tv_cancel);
        tv_total = inflate.findViewById(R.id.tv_total);
        tv_homework_check = inflate.findViewById(R.id.tv_homework_check);
        tv_homework_tutorship = inflate.findViewById(R.id.tv_homework_tutorship);
        tv_recharge = inflate.findViewById(R.id.tv_recharge);
        tv_ensure = inflate.findViewById(R.id.tv_ensure);
        tv_cancel.setOnClickListener(this);
        tv_total.setOnClickListener(this);
        tv_homework_check.setOnClickListener(this);
        tv_homework_tutorship.setOnClickListener(this);
        tv_recharge.setOnClickListener(this);
        tv_ensure.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_total:
                label=4;
                tv_total.setTextColor(context.getColor(R.color.font_color_select));
                tv_total.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_bg));
                tv_homework_check.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_homework_check.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                tv_homework_tutorship.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_homework_tutorship.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                tv_recharge.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_recharge.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                break;
            case R.id.tv_homework_check:
                label=1;
                tv_total.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_total.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                tv_homework_check.setTextColor(context.getColor(R.color.font_color_select));
                tv_homework_check.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_bg));
                tv_homework_tutorship.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_homework_tutorship.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                tv_recharge.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_recharge.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                break;
            case R.id.tv_homework_tutorship:
                label=2;
                tv_total.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_total.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                tv_homework_check.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_homework_check.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                tv_homework_tutorship.setTextColor(context.getColor(R.color.font_color_select));
                tv_homework_tutorship.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_bg));
                tv_recharge.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_recharge.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                break;
            case R.id.tv_recharge:
                label=3;
                tv_total.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_total.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                tv_homework_check.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_homework_check.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                tv_homework_tutorship.setTextColor(context.getColor(R.color.font_color_unselected));
                tv_homework_tutorship.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_unselect_bg));
                tv_recharge.setTextColor(context.getColor(R.color.font_color_select));
                tv_recharge.setBackground(context.getResources().getDrawable(R.drawable.wallet_tv_bg));
                break;
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.tv_ensure:
                if(onItemClickListener!=null){
                    onItemClickListener.onItemClick(label);
                }
                dismiss();
                break;
        }

    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int label);
    }
    @Override
    public void showAsDropDown(View parent) {
        try {
            if (Build.VERSION.SDK_INT >= 24) {
                int[] location = new int[2];
                parent.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1] + parent.getHeight();
                this.showAtLocation(parent, Gravity.BOTTOM, x, y);
            } else {
                this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
            }

            isDismiss = false;
            cl_bill_filter.startAnimation(animationIn);
            darkenBackground(0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void darkenBackground(Float bgcolor){
        WindowManager.LayoutParams lp = ((Activity)context).getWindow().getAttributes();
        lp.alpha = bgcolor;
        ((Activity)context).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        ((Activity)context).getWindow().setAttributes(lp);

    }
    @Override
    public void dismiss() {
        if (isDismiss) {
            return;
        }
        isDismiss = true;
        cl_bill_filter.startAnimation(animationOut);
        dismiss();
        animationOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isDismiss = false;
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN){
                    dismiss4Pop();
                } else {
                    BillFilterPopup.super.dismiss();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        darkenBackground(1f);
    }

    /**
     * 在android4.1.1和4.1.2版本关闭PopWindow
     */
    private void dismiss4Pop() {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                BillFilterPopup.super.dismiss();
            }
        });
    }
}
