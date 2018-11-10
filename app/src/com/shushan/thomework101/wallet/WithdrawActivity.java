package com.shushan.thomework101.wallet;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class WithdrawActivity extends BaseActivity implements View.OnClickListener{
@Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
@Bind(R.id.rl_wallet_add)
    RelativeLayout rl_wallet_add;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_withdraw);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        rl_wallet_add.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tv_actionbar.setText(getResources().getString(R.string.wallet_withdraw));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_wallet_add:
                startActivitys(this,AddCardActivity.class);
                break;
        }
    }
}
