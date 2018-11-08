package com.shushan.thomework101.homepage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.nimlib.sdk.AbortableFuture;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.shushan.thomework101.Constants;
import com.shushan.thomework101.GlideApp;
import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;
import com.shushan.thomework101.HttpHelper.service.entity.homework.HomeworkDetails;
import com.shushan.thomework101.HttpHelper.service.entity.orders.GoAccept;
import com.shushan.thomework101.HttpHelper.service.entity.orders.GoCheck;
import com.shushan.thomework101.HttpHelper.service.presenter.GoAcceptPresenter;
import com.shushan.thomework101.HttpHelper.service.presenter.GoCheckPresenter;
import com.shushan.thomework101.HttpHelper.service.presenter.HomeworkDetailsPresenter;
import com.shushan.thomework101.HttpHelper.service.view.GoAcceptView;
import com.shushan.thomework101.HttpHelper.service.view.GoCheckView;
import com.shushan.thomework101.HttpHelper.service.view.HomeworkDetailsView;
import com.shushan.thomework101.R;
import com.shushan.thomework101.banner.ImageViewRoundRect;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.third.AuthPreferences;
import com.shushan.thomework101.tutorship.ConnectionActivity;
import com.shushan.thomework101.tutorship.HomeworkCheckActivity;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;
import com.shushan.thomework101.xbanner.BannerLayout;
import com.shushan.thomework101.xbanner.WebBannerAdapter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class HomeworkOrdersDetailsActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_back)
    ImageView iv_back;
    @Bind(R.id.recycler)
    BannerLayout recyclerBanner;
    @Bind(R.id.btn_orders)
    Button btn_orders;
    @Bind(R.id.iv_orders_teacher_visa)
    ImageViewRoundRect iv_orders_teacher_visa;
    @Bind(R.id.tv_orders_teacher_name)
    TextView tv_orders_teacher_name;
    @Bind(R.id.tv_orders_teaching_grade)
    TextView tv_orders_teaching_grade;
    @Bind(R.id.tv_orders_check_price)
    TextView tv_orders_check_price;
    @Bind(R.id.tv_orders_check_default_price)
    TextView tv_orders_check_default_price;
    @Bind(R.id.rl_orders_details_time)
    RelativeLayout rl_orders_details_time;
    @Bind(R.id.tv_orders_details_time)
    TextView tv_orders_details_time;
    @Bind(R.id.tv_orders_go_help)
    TextView tv_orders_go_help;
    @Bind(R.id.tv_orders_wait_go_help)
    TextView tv_orders_wait_go_help;

    private HomepageData homepageData;
    private List<String> list;
    private HomeworkDetailsPresenter homeworkDetailsPresenter;
    private int tid;
    private String token;
    private String oid;
    private GoCheckPresenter goCheckPresenter;
    private GoAcceptPresenter goAcceptPresenter;
    private AbortableFuture<LoginInfo> loginRequest;
    private String third_id;
    private String third_token;
    private boolean isCreate=true;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_orders_details);
        changeStatusBarTextImgColor(true);
    }

    @Override
    protected void initViews() {
        list = new ArrayList<>();
        homeworkDetailsPresenter = new HomeworkDetailsPresenter(this);
        homeworkDetailsPresenter.onCreate(Constants.BASE_URL);
        homeworkDetailsPresenter.attachView(homeworkDetailsView);
        goCheckPresenter = new GoCheckPresenter(this);
        goCheckPresenter.onCreate(Constants.BASE_URL);
        goCheckPresenter.attachView(goCheckView);
        goAcceptPresenter = new GoAcceptPresenter(this);
        goAcceptPresenter.onCreate(Constants.BASE_URL);
        goAcceptPresenter.attachView(goAcceptView);

    }

    @Override
    protected void initEvents() {
        tv_orders_go_help.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        btn_orders.setOnClickListener(this);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            homepageData = (HomepageData) bundle.getSerializable("homepageData");
            LogUtils.d("homepageData:" + homepageData.toString());
        }
        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        tid = sharedPreferences.getInt("tid", 0);
        token = sharedPreferences.getString("token", "");
        third_id = sharedPreferences.getString("third_id", "");
        third_token = sharedPreferences.getString("third_token", "");
        if (homepageData != null) {
            if (homepageData.getLabel() == 1) {
                tv_orders_check_price.setText(getResources().getString(R.string.homework_orders_details_check_price));
                tv_orders_check_default_price.setText(homepageData.getPrice() + getResources().getString(R.string.dollar));
                rl_orders_details_time.setVisibility(View.GONE);
                btn_orders.setVisibility(View.VISIBLE);
                btn_orders.setText(getResources().getString(R.string.homepage_go_check));
                tv_orders_go_help.setVisibility(View.GONE);
                tv_orders_wait_go_help.setVisibility(View.GONE);
            }
            if (homepageData.getIs_receive() == 1) {

                if (homepageData.getLabel()==2) {

                    tv_orders_check_price.setText(getResources().getString(R.string.homework_orders_details_help_price));
                    tv_orders_check_default_price.setText(homepageData.getPrice() + getResources().getString(R.string.dollar_per_minute));
                    rl_orders_details_time.setVisibility(View.VISIBLE);
                    try {
                        tv_orders_details_time.setText(getResources().getString(R.string.homework_orders_details_time) + Utils.longToString(homepageData.getStart_time() * 1000L, "MM-dd HH:mm"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    btn_orders.setVisibility(View.VISIBLE);
                    btn_orders.setText(getResources().getString(R.string.homepage_take_orders));
                    tv_orders_go_help.setVisibility(View.GONE);
                    tv_orders_wait_go_help.setVisibility(View.GONE);

                }else if(homepageData.getLabel()==3){

                    tv_orders_check_price.setText(getResources().getString(R.string.homework_orders_details_help_price));
                    tv_orders_check_default_price.setText(homepageData.getPrice() + getResources().getString(R.string.dollar_per_minute));
                    rl_orders_details_time.setVisibility(View.GONE);
                    btn_orders.setVisibility(View.VISIBLE);
                    btn_orders.setText(getResources().getString(R.string.homepage_take_orders));
                    tv_orders_go_help.setVisibility(View.GONE);
                    tv_orders_wait_go_help.setVisibility(View.GONE);

                }
            } else {

                if (homepageData.getLabel() == 2) {
                    tv_orders_check_price.setText(getResources().getString(R.string.homework_orders_details_help_price));
                    tv_orders_check_default_price.setText(homepageData.getPrice() + getResources().getString(R.string.dollar_per_minute));
                    rl_orders_details_time.setVisibility(View.VISIBLE);
                    try {
                        tv_orders_details_time.setText(getResources().getString(R.string.homework_orders_details_time) + Utils.longToString(homepageData.getStart_time() * 1000L, "MM-dd HH:mm"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    btn_orders.setVisibility(View.GONE);
                    //todo 如果预约时间到显示去辅导
                    if(System.currentTimeMillis()<homepageData.getStart_time()*1000L){
                        tv_orders_go_help.setVisibility(View.GONE);
                        tv_orders_wait_go_help.setVisibility(View.VISIBLE);
                        try {
                            tv_orders_wait_go_help.setText(getResources().getString(R.string.homepage_distance_learning)+
                                    Utils.longToString((homepageData.getStart_time()-System.currentTimeMillis()/1000),"HH时mm分"));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }else{
                        tv_orders_go_help.setVisibility(View.VISIBLE);
                        tv_orders_wait_go_help.setVisibility(View.GONE);
                    }

                } else if (homepageData.getLabel() == 3) {
                    tv_orders_check_price.setText(getResources().getString(R.string.homework_orders_details_help_price));
                    tv_orders_check_default_price.setText(homepageData.getPrice() + getResources().getString(R.string.dollar_per_minute));
                    rl_orders_details_time.setVisibility(View.VISIBLE);
                    try {
                        tv_orders_details_time.setText(getResources().getString(R.string.homework_orders_details_time) + Utils.longToString(homepageData.getStart_time() * 1000L, "MM-dd HH:mm"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    btn_orders.setVisibility(View.GONE);
                    tv_orders_go_help.setVisibility(View.VISIBLE);
                    tv_orders_wait_go_help.setVisibility(View.GONE);

                }
            }

            tv_orders_teaching_grade.setText(homepageData.getGrade());
            tv_orders_teacher_name.setText(homepageData.getUsername());

            GlideApp.with(this).load(homepageData.getTrait()).error(R.drawable.visa).into(iv_orders_teacher_visa);

            for (int i = 0; i < homepageData.getJob_pic().length; i++) {
                list.add(homepageData.getJob_pic()[i]);
            }
            WebBannerAdapter webBannerAdapter = new WebBannerAdapter(this, list);
            webBannerAdapter.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(HomeworkOrdersDetailsActivity.this, "点击了第  " + position + "  项", Toast.LENGTH_SHORT).show();
                }
            });

            recyclerBanner.setAdapter(webBannerAdapter);

            if (!TextUtils.isEmpty(token)) {

                homeworkDetailsPresenter.getHomeworkDetailsMsg(tid + "", token, homepageData.getJob_id() + "");
            }
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_orders:
                if (homepageData.getLabel() == 1) {
                    //goCheckPresenter.getGoChecksMsg(tid + "", token, homepageData.getJob_id() + "");

                    final String account =third_id;
                    final String token = third_token;
                    // 登录
                    loginRequest = NIMClient.getService(AuthService.class).login(new LoginInfo(account, token));
                    loginRequest.setCallback(new RequestCallback<LoginInfo>() {
                        @Override
                        public void onSuccess(LoginInfo param) {
                            LogUtils.e("LoginInfo  onSuccess");
                            saveLoginInfo(account, token);
                            Intent intent = new Intent(HomeworkOrdersDetailsActivity.this, HomeworkCheckActivity.class);
                            //intent.putExtra("oid", goCheck.getData().getOid());
                            intent.putExtra("homepageData", homepageData);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailed(int code) {
                            if (code == 302 || code == 404) {
                                LogUtils.e("LoginInfo  onFailed");
                            } else {
                                LogUtils.e("LoginInfo  onFailed");

                            }
                        }
                        @Override
                        public void onException(Throwable exception) {
                            //onLoginDone();
                        }
                    });


                    /*Intent intent = new Intent(HomeworkOrdersDetailsActivity.this, HomeworkCheckActivity.class);
                    //intent.putExtra("oid", goCheck.getData().getOid());
                    intent.putExtra("homepageData", homepageData);
                    startActivity(intent);*/

                } else {
                    if (homepageData.getIs_receive() == 1) {

                        if (homepageData.getLabel() == 2) {
                            if(!TextUtils.isEmpty(token)){
                                goAcceptPresenter.getGoAcceptMsg(tid+"",token,homepageData.getJob_id()+"");
                            }


                        } else {

                            if(!TextUtils.isEmpty(token)){
                                goAcceptPresenter.getGoAcceptMsg(tid+"",token,homepageData.getJob_id()+"");
                            }
                        }
                    }
                    /*else {
                        if (homepageData.getLabel() == 2) {

                        } else {

                        }
                    }*/
                }
                break;
                case R.id.tv_orders_go_help:
                    if(!TextUtils.isEmpty(token)){
                        goAcceptPresenter.getGoAcceptMsg(tid+"",token,homepageData.getJob_id()+"");
                    }
                    break;
        }
    }

    private void saveLoginInfo(final String account, final String token) {
        AuthPreferences.saveUserAccount(account);
        AuthPreferences.saveUserToken(token);
    }

    private GoAcceptView goAcceptView=new GoAcceptView() {
        @Override
        public void onSuccess(GoAccept goAccept) {
            if (goAccept != null && goAccept.getError() == 0) {
                Intent intent=new Intent(HomeworkOrdersDetailsActivity.this, ConnectionActivity.class);
                intent.putExtra("homepageData",homepageData);
                startActivity(intent);
            }
        }

        @Override
        public void onError(String result) {

        }
    };
    private GoCheckView goCheckView = new GoCheckView() {
        @Override
        public void onSuccess(GoCheck goCheck) {
            if (goCheck != null && goCheck.getError() == 0) {

                Intent intent = new Intent(HomeworkOrdersDetailsActivity.this, HomeworkCheckActivity.class);
                intent.putExtra("oid", goCheck.getData().getOid());
                intent.putExtra("homepageData", homepageData);
                startActivity(intent);
            }
        }

        @Override
        public void onError(String result) {

        }
    };
    private HomeworkDetailsView homeworkDetailsView = new HomeworkDetailsView() {
        @Override
        public void onSuccess(HomeworkDetails homeworkDetails) {
            if (homeworkDetails != null && homeworkDetails.getError() == 0) {
                LogUtils.d(homeworkDetails.toString());
                oid = homeworkDetails.getData().getOid();
            }
        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
