package com.shushan.thomework101.login;

import android.view.View;
import android.widget.Button;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class SetIdentityActivity extends BaseActivity implements View.OnClickListener{
@Bind(R.id.btn_identity_ok)
    Button btn_identity_ok;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_set_identity);
        changeStatusBarTextImgColor(true);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        btn_identity_ok.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_identity_ok:
                startActivitys(this,PersonalInfoActivity.class);
                break;
        }
    }
}
