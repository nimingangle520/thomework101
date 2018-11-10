package com.shushan.thomework101.wallet;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;

import butterknife.Bind;

public class MineCardActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.iv_actionbar_right)
    ImageView iv_actionbar_right;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_mine_card);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        iv_actionbar_right.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        iv_actionbar_right.setVisibility(View.VISIBLE);
        iv_actionbar_right.setImageResource(R.drawable.add_card_gray);
        tv_actionbar.setText(getResources().getString(R.string.wallet_mine_wallet));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_actionbar_right:
                startActivitys(this,AddCardActivity.class);
                break;
        }
    }
}
