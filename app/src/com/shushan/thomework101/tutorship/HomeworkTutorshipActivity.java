package com.shushan.thomework101.tutorship;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;
import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.StartTopicViewpagerAdapter;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.utils.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

public class HomeworkTutorshipActivity extends BaseActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    @Bind(R.id.vp_tutorship)
    ViewPager vp_tutorship;
    @Bind(R.id.tv_tutorship_indicator)
    TextView tv_tutorship_indicator;
    @Bind(R.id.rl_paint)
    RelativeLayout rl_paint;
    @Bind(R.id.ll_paint_color)
    LinearLayout ll_paint_color;
    @Bind(R.id.sb_tutorship)
    VerticalSeekBar sb_tutorship;

    boolean isShowPaint = false;
    private StartTopicViewpagerAdapter tutorshipViewpagerAdapter;
    private HomepageData homepageData;
    private String oid;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_homework_tutorship);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        rl_paint.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
                homepageData = (HomepageData) intent.getSerializableExtra("homepageData");
                oid = intent.getStringExtra("oid");
                LogUtils.d("oid:"+oid+"\nhomepageData:" + homepageData.toString());
        }

        if (homepageData.getJob_pic() != null && homepageData.getJob_pic().length > 0) {
            tutorshipViewpagerAdapter = new StartTopicViewpagerAdapter(new ArrayList(Arrays.asList(homepageData.getJob_pic())), this);
            vp_tutorship.setAdapter(tutorshipViewpagerAdapter);
            vp_tutorship.setOnPageChangeListener(this);
            tv_tutorship_indicator.setText(vp_tutorship.getCurrentItem() + 1 + "/" + homepageData.getJob_pic().length);
        } else {
            tv_tutorship_indicator.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        tv_tutorship_indicator.setText(vp_tutorship.getCurrentItem() + 1 + "/" + homepageData.getJob_pic().length);

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_paint:
                if(!isShowPaint){
                    ll_paint_color.setVisibility(View.VISIBLE);
                    sb_tutorship.setVisibility(View.VISIBLE);
                    isShowPaint=true;
                }else{
                    ll_paint_color.setVisibility(View.INVISIBLE);
                    sb_tutorship.setVisibility(View.INVISIBLE);
                    isShowPaint=false;
                }
                break;
        }
    }
}
