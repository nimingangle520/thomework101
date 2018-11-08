package com.shushan.thomework101.homepage;

import android.view.View;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class RealNameActivity extends BaseActivity {
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.tv_right)
    TextView tv_right;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_real_name);
    }

    @Override
    protected void initViews() {
        tv_right.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        tv_actionbar.setText(getResources().getString(R.string.homepage_info_real_name));
        tv_right.setText(getResources().getString(R.string.personal_introduce_save));
    }
}
