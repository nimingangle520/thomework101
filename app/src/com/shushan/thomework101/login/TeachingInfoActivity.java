package com.shushan.thomework101.login;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.homepage.HomepageActivity;

import butterknife.Bind;

public class TeachingInfoActivity extends BaseActivity implements View.OnClickListener{

@Bind(R.id.rl_teaching_grade)
    RelativeLayout rl_teaching_grade;
@Bind(R.id.btn_teaching_next)
    Button btn_teaching_next;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_teaching_info);
        changeStatusBarTextImgColor(true);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        rl_teaching_grade.setOnClickListener(this);
        btn_teaching_next.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_teaching_grade:
                startActivitys(this,TeachingGradeActivity.class);
                break;
            case R.id.btn_teaching_next:
                startActivitys(this, HomepageActivity.class);
                break;

        }
    }
}
