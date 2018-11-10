package com.shushan.thomework101.wallet;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class AddCardActivity extends BaseActivity implements View.OnClickListener{
@Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
@Bind(R.id.btn_wallet_add_card_next)
    Button btn_wallet_add_card_next;
    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_add_card);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        btn_wallet_add_card_next.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        tv_actionbar.setText(getResources().getString(R.string.wallet_add_card));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_wallet_add_card_next:
                startActivitys(this,BindCardActivity.class);
                break;
        }
    }
}
