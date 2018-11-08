package com.shushan.thomework101.tutorship;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shushan.thomework101.Constants;
import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;
import com.shushan.thomework101.HttpHelper.service.entity.orders.GoTutorship;
import com.shushan.thomework101.HttpHelper.service.presenter.GoTutorshipPresenter;
import com.shushan.thomework101.HttpHelper.service.view.GoTutorshipView;
import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.StartTopicViewpagerAdapter;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.utils.LogUtils;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;

public class StartTutorshipActivity extends BaseActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{
@Bind(R.id.btn_start_tutorship)
    Button btn_start_tutorship;
@Bind(R.id.vp_start_tutorship)
    ViewPager vp_start_tutorship;
@Bind(R.id.tv_start_tutorship_indicator)
    TextView tv_start_tutorship_indicator;
@Bind(R.id.iv_start_tutorship_choose)
    ImageView iv_start_tutorship_choose;

    private StartTopicViewpagerAdapter startTutorshipViewpagerAdapter;
    private String oid;
    private HomepageData homepageData;
    private MenuPopupWindow menuPopupWindow;
    private GoTutorshipPresenter goTutorshipPresenter;
    private int tid;
    private String token;
    private SharedPreferences sharedPreferences;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_start_tutorship);
    }

    @Override
    protected void initViews() {
        iv_start_tutorship_choose.setOnClickListener(this);
        goTutorshipPresenter = new GoTutorshipPresenter(this);
        goTutorshipPresenter.onCreate(Constants.BASE_URL);
        goTutorshipPresenter.attachView(goTutorshipView);
    }

    @Override
    protected void initEvents() {
        btn_start_tutorship.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        tid = sharedPreferences.getInt("tid", 0);
        token = sharedPreferences.getString("token", "");
        Intent intent=getIntent();
        if(intent!=null){
            homepageData = (HomepageData) intent.getSerializableExtra("homepageData");
            LogUtils.d("\nhomepageData:"+homepageData);
        }
        if(homepageData!=null){
            if(homepageData.getJob_pic()!=null&&homepageData.getJob_pic().length>0){

                startTutorshipViewpagerAdapter = new StartTopicViewpagerAdapter(new ArrayList(Arrays.asList(homepageData.getJob_pic())),this);
                vp_start_tutorship.setAdapter(startTutorshipViewpagerAdapter);
                vp_start_tutorship.setOnPageChangeListener(this);
                tv_start_tutorship_indicator.setText(vp_start_tutorship.getCurrentItem()+1+"/"+homepageData.getJob_pic().length);
            }else{
                tv_start_tutorship_indicator.setVisibility(View.INVISIBLE);
            }
        }
    }
    private GoTutorshipView goTutorshipView=new GoTutorshipView() {
        @Override
        public void onSuccess(GoTutorship goTutorship) {
            if(goTutorship!=null&&goTutorship.getError()==0){
                oid= goTutorship.getData().getOid();
                Intent intent = new Intent(StartTutorshipActivity.this,HomeworkTutorshipActivity.class);
                intent.putExtra("oid", goTutorship.getData().getOid());
                intent.putExtra("homepageData", homepageData);
                startActivity(intent);
            }
        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start_tutorship:
                goTutorshipPresenter.getGoTutorshipMsg(tid+"",token,homepageData.getJob_id()+"");

                break;
            case R.id.iv_start_tutorship_choose:

                menuPopupWindow = new MenuPopupWindow(this, new MenuPopupWindow.OnItemClickListener() {
                    @Override
                    public void onItemClick(PopupWindow popupWindow, int position) {

                        if(position==1){

                            if(homepageData.getLabel()==1){
                                  String title= getResources().getString(R.string.notice_msg);
                            }
                            new CommomDialog(StartTutorshipActivity.this, R.style.dialog, getResources().getString(R.string.notice_msg), new CommomDialog.OnCloseListener() {
                                @Override
                                public void onClick(Dialog dialog, boolean confirm) {
                                    if(confirm){


                                        dialog.dismiss();
                                    }

                                }
                            }).setTitle(getResources().getString(R.string.notice)).show();


                        }else{

                        }
                        popupWindow.dismiss();
                    }
                });
                int[] location = new int[2];
                iv_start_tutorship_choose.getLocationOnScreen(location);
                menuPopupWindow.showAtLocation(iv_start_tutorship_choose, Gravity.NO_GRAVITY, location[0] - iv_start_tutorship_choose.getWidth(), location[1]+iv_start_tutorship_choose.getHeight());

                break;
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        tv_start_tutorship_indicator.setText(vp_start_tutorship.getCurrentItem()+1+"/"+homepageData.getJob_pic().length);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
