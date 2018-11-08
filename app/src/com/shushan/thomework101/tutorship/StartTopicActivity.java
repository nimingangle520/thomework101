package com.shushan.thomework101.tutorship;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;
import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.StartTopicViewpagerAdapter;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.utils.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

public class StartTopicActivity extends BaseActivity implements ViewPager.OnPageChangeListener,View.OnClickListener{
    @Bind(R.id.vp_start_topic)
    ViewPager vp_start_topic;
    @Bind(R.id.tv_vp_indicator)
    TextView tv_vp_indicator;
    @Bind(R.id.btn_start_topic)
    Button btn_start_topic;

    private StartTopicViewpagerAdapter startTopicViewpagerAdapter;
    private HomepageData homepageData;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_start_topic);

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        btn_start_topic.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            if (intent != null) {
                homepageData = (HomepageData) intent.getSerializableExtra("homepageData");
                LogUtils.d("homepageData:" + homepageData.toString());
            }
        }

        if(homepageData.getJob_pic()!=null&&homepageData.getJob_pic().length>0){

            startTopicViewpagerAdapter = new StartTopicViewpagerAdapter(new ArrayList(Arrays.asList(homepageData.getJob_pic())),this);
            vp_start_topic.setAdapter(startTopicViewpagerAdapter);
            vp_start_topic.setOnPageChangeListener(this);
            tv_vp_indicator.setText(vp_start_topic.getCurrentItem()+1+"/"+homepageData.getJob_pic().length);

        }else{
            tv_vp_indicator.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        tv_vp_indicator.setText(vp_start_topic.getCurrentItem()+1+"/"+homepageData.getJob_pic().length);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_topic:
                Intent intent = new Intent(this,StartTutorshipActivity.class);
                intent.putExtra("homepageData", homepageData);
                startActivity(intent);
                break;
        }
    }
}
