package com.shushan.thomework101.tutorship;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nimlib.sdk.rts.RTSCallback;
import com.netease.nimlib.sdk.rts.RTSManager2;
import com.netease.nimlib.sdk.rts.model.RTSData;
import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;
import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.HomeCheckViewpagerAdapter;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.utils.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

public class HomeworkCheckActivity extends BaseActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
@Bind(R.id.vp_check)
    ViewPager vp_check;
@Bind(R.id.tv_check_indicator)
    TextView tv_check_indicator;
    private HomeCheckViewpagerAdapter checkViewpagerAdapter;
    private HomepageData homepageData;
    private String oid;
    private boolean isOpen;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_homework_check);
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {

        Intent intent=getIntent();
        if(intent!=null){
            homepageData = (HomepageData) intent.getSerializableExtra("homepageData");
            oid = intent.getStringExtra("oid");
            LogUtils.d("oid:"+ oid +"\nhomepageData:" + homepageData.toString());
        }

        initRTSSession();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        tv_check_indicator.setText(vp_check.getCurrentItem() + 1 + "/" + homepageData.getJob_pic().length);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    private void initRTSSession() {

        RTSManager2.getInstance().createSession("check-"+homepageData.getJob_id(), "", new RTSCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(HomeworkCheckActivity.this, "创建多人白板房间成功", Toast.LENGTH_SHORT).show();
                isOpen = true;
                joinRTSSession();
            }

            @Override
            public void onFailed(int i) {
                if (i == 417) {
                    Toast.makeText(HomeworkCheckActivity.this, "多人白板房间已经被预定", Toast.LENGTH_SHORT).show();
                    isOpen=false;
                    joinRTSSession();
                }
                LogUtils.d( "create rts session failed, code:" + i);
            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
    }

    // 加入多人白板session
    private void joinRTSSession() {

        LogUtils.i( "rts record is open->" + isOpen);
        RTSManager2.getInstance().joinSession("check-"+homepageData.getJob_id(), isOpen, new RTSCallback<RTSData>() {
            @Override
            public void onSuccess(RTSData rtsData) {
                LogUtils.i( "rts extra:" + rtsData.getExtra());
                // 主播的白板默认为开启状态
                //if (roomInfo.getCreator().equals(DemoCache.getAccount())) {
                    //ChatRoomMemberCache.getInstance().setRTSOpen(true);

                if (homepageData.getJob_pic() != null && homepageData.getJob_pic().length > 0) {
                    checkViewpagerAdapter = new HomeCheckViewpagerAdapter("check-"+homepageData.getJob_id(),isOpen,new ArrayList(Arrays.asList(homepageData.getJob_pic())), HomeworkCheckActivity.this);
                    vp_check.setAdapter(checkViewpagerAdapter);
                    vp_check.setOnPageChangeListener(HomeworkCheckActivity.this);
                    tv_check_indicator.setText(vp_check.getCurrentItem() + 1 + "/" + homepageData.getJob_pic().length);
                } else {
                    tv_check_indicator.setVisibility(View.INVISIBLE);
                }

                //}
                Toast.makeText(HomeworkCheckActivity.this, "加入多人白板房间成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(int i) {
                LogUtils.d( "join rts session failed, code:" + i);
                Toast.makeText(HomeworkCheckActivity.this, "join rts session failed, code:" + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onException(Throwable throwable) {

            }
        });

    }
}
