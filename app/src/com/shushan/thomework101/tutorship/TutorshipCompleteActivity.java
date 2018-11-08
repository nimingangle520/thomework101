package com.shushan.thomework101.tutorship;

import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class TutorshipCompleteActivity extends BaseActivity {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.tv_right_title)
    TextView tv_right_title;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_tutorship_complete);
        changeStatusBarTextImgColor(true);
    }

    @Override
    protected void initViews() {
        iv_back.setVisibility(View.INVISIBLE);
        tv_right_title.setText(getResources().getString(R.string.tutorship_complete));
        tv_title.setText(getResources().getString(R.string.tutorship_finish));
        TextPaint textPaint=tv_title.getPaint();
        textPaint.setFakeBoldText(true);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }
}
