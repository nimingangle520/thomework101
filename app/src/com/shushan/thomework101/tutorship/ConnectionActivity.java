package com.shushan.thomework101.tutorship;

import android.content.Intent;
import android.text.TextPaint;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;
import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.utils.LogUtils;

import butterknife.Bind;

public class ConnectionActivity extends BaseActivity implements View.OnClickListener{

@Bind(R.id.iv_back)
    ImageView iv_back;
@Bind(R.id.tv_title)
    TextView tv_title;
@Bind(R.id.btn_tutorship_connect)
    Button btn_tutorship_connect;
    private int jobid;
    private HomepageData homepageData;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_connection);

    }

    @Override
    protected void initViews() {
        iv_back.setBackgroundResource(R.drawable.go_back_white);
        tv_title.setVisibility(View.VISIBLE);
        tv_title.setText(getResources().getString(R.string.tutorship_connection_students));
        tv_title.setTextColor(getResources().getColor(R.color.white));
        TextPaint tp = tv_title.getPaint();
        tp.setFakeBoldText(true);
    }

    @Override
    protected void initEvents() {
        btn_tutorship_connect.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            homepageData = (HomepageData) intent.getSerializableExtra("homepageData");
            LogUtils.d("homepageData:" + homepageData.toString());
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_tutorship_connect:
                Intent intent = new Intent(this,StartTopicActivity.class);
                intent.putExtra("homepageData", homepageData);
                startActivity(intent);
            break ;
        }
    }
}
