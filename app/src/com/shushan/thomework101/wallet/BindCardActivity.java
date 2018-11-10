package com.shushan.thomework101.wallet;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;
public class BindCardActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.tv_wallet_ensure_bind)
    Button tv_wallet_ensure_bind;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_bind_card);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        tv_wallet_ensure_bind.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tv_actionbar.setText(getResources().getString(R.string.wallet_bind_card));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_wallet_ensure_bind:
                startActivitys(this,MineCardActivity.class);
                break;
        }
    }
}
