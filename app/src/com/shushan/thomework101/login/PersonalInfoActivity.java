package com.shushan.thomework101.login;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class PersonalInfoActivity extends BaseActivity implements View.OnClickListener{
@Bind(R.id.rl_personal_introduce)
    RelativeLayout rl_personal_introduce;
@Bind(R.id.btn_personal_next)
    Button btn_personal_next;
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_personal_info);
        changeStatusBarTextImgColor(true);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        rl_personal_introduce.setOnClickListener(this);
        btn_personal_next.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_personal_introduce:
                startActivitys(this,PersonalIntroduceActivity.class);
                break;
             case R.id.btn_personal_next:
                 startActivitys(this,TeachingInfoActivity.class);
                 break;
        }
    }
}
