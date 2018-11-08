package com.shushan.thomework101.homepage;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class EducationActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.tv_right)
    TextView tv_right;
    @Bind(R.id.rl_education)
    RelativeLayout rl_education;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_education);
    }

    @Override
    protected void initViews() {
        tv_right.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initEvents() {
        rl_education.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tv_actionbar.setText(getResources().getString(R.string.homepage_info_education));
        tv_right.setText(getResources().getString(R.string.personal_introduce_save));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_education:
                startActivitys(this,SchoolActivity.class);
                break;
        }
    }
}
