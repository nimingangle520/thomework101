package com.shushan.thomework101;

import android.content.Intent;
import android.os.Handler;
import android.view.WindowManager;

import com.shushan.thomework101.base.BaseActivity;

public class LaunchActivity extends BaseActivity {


    @Override
    protected void initContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        setContentView(R.layout.activity_launch);
    }

    @Override
    protected void initViews() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivitys(mContext,MainActivity.class);
                LaunchActivity.this.finish();
            }
        },3000);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

    }
}
