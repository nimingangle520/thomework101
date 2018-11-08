package com.shushan.thomework101.homepage;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class InfoActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.rl_info_real_time)
    RelativeLayout rl_info_real_time;
    @Bind(R.id.rl_info_education)
    RelativeLayout rl_info_education;
    @Bind(R.id.rl_info_tea_qualification)
    RelativeLayout rl_info_tea_qualification;
    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_info);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        rl_info_real_time.setOnClickListener(this);
        rl_info_education.setOnClickListener(this);
        rl_info_tea_qualification.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        tv_actionbar.setText(getResources().getString(R.string.homepage_info));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_info_real_time:
                startActivitys(this,RealNameActivity.class);
                break;
            case R.id.rl_info_education:
                 startActivitys(this,EducationActivity.class);
                break;
            case R.id.rl_info_tea_qualification:
                startActivitys(this,TeaQualificationActivity.class);
                break;
        }
    }
}
