package com.shushan.thomework101.homepage;

import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class TeaQualificationActivity extends BaseActivity {

    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_tea_qualification);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        tv_actionbar.setText(getResources().getString(R.string.homepage_info_tea_qualification));

    }
}
