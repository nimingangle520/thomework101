package com.shushan.thomework101.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.Constants;
import com.shushan.thomework101.HttpHelper.service.entity.register.Captcha;
import com.shushan.thomework101.HttpHelper.service.entity.register.VerifyCaptcha;
import com.shushan.thomework101.HttpHelper.service.presenter.CaptchaPresenter;
import com.shushan.thomework101.HttpHelper.service.presenter.VerifyCaptchaPresenter;
import com.shushan.thomework101.HttpHelper.service.view.CaptchaView;
import com.shushan.thomework101.HttpHelper.service.view.VerifyCaptchaView;
import com.shushan.thomework101.MainActivity;
import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.utils.LogUtils;

import butterknife.Bind;

public class RegistActivity extends BaseActivity implements View.OnClickListener{
@Bind(R.id.btn_reg_next)
    Button btn_reg_next;
@Bind(R.id.iv_back)
    ImageView iv_back;
@Bind(R.id.et_reg_phone)
    EditText et_reg_phone;
@Bind(R.id.et_reg_captcha)
    EditText et_reg_captcha;
@Bind(R.id.tv_reg_captcha)
    TextView tv_reg_captcha;
    private String captcha;
    private String phoneNum;
    private CaptchaPresenter captchaPresenter;
    private String imei;
    private VerifyCaptchaPresenter verifyCaptchaPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_regist);
        changeStatusBarTextImgColor(true);
    }

    @Override
    protected void initViews() {
        et_reg_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        et_reg_captcha.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        captchaPresenter = new CaptchaPresenter(this);
        captchaPresenter.onCreate(Constants.BASE_URL);
        captchaPresenter.attachView(captchaView);
        verifyCaptchaPresenter = new VerifyCaptchaPresenter(this);
        verifyCaptchaPresenter.onCreate(Constants.BASE_URL);
        verifyCaptchaPresenter.attachView(verifyCaptchaView);
    }

    @Override
    protected void initEvents() {
        btn_reg_next.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        tv_reg_captcha.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_reg_next:
                captcha = et_reg_captcha.getText().toString().trim();

                if(!TextUtils.isEmpty(phoneNum)&&!TextUtils.isEmpty(captcha)){

                    verifyCaptchaPresenter.getVerifyCaptchaMsg(phoneNum,captcha);

                }
                break;
            case R.id.iv_back:
                startActivitys(this,MainActivity.class);
                break;
            case R.id.tv_reg_captcha:
                phoneNum = et_reg_phone.getText().toString().trim();
                if(!TextUtils.isEmpty(phoneNum)&&isMobileNO(phoneNum)){
                  captchaPresenter.getCaptchaMsg(phoneNum);
                }
                break;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
        String phoneNum=sharedPreferences.getString("phoneNum",getResources().getString(R.string.login_please_input_phone_number));
        if(phoneNum.equals(getResources().getString(R.string.login_please_input_phone_number))){
            et_reg_phone.setHint(phoneNum);
        }else{
            et_reg_phone.setText(phoneNum);
        }
    }

    /** 判断字符串是否符合手机号码格式
     *
     * *@param str
     *  @return 待检测的字符串
     */
    public static boolean isMobileNO(String mobileNums) {

        String telRegex = "^((13[0-9])|(14[5,7,9])|(15[^4])|(18[0-9])|(17[0,1,3,5,6,7,8]))\\d{8}$";
        // "[1]"代表下一位为数字可以是几，"[0-9]"代表可以为0-9中的一个，"[5,7,9]"表示可以是5,7,9中的任意一位,[^4]表示除4以外的任何一个,
        // \\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    private VerifyCaptchaView verifyCaptchaView = new VerifyCaptchaView() {
        @Override
        public void onSuccess(VerifyCaptcha verifyCaptcha) {

            if(verifyCaptcha!=null&&verifyCaptcha.getError()==0){
                LogUtils.d(verifyCaptcha.toString());
                Intent intent=new Intent(RegistActivity.this,SetPswActivity.class);
                intent.putExtra("code",captcha);
                RegistActivity.this.startActivity(intent);

            }


        }

        @Override
        public void onError(String result) {
            LogUtils.e(result);
        }
    };
    private CaptchaView captchaView=new CaptchaView() {
        @Override
        public void onSuccess(Captcha captcha) {
            if(captcha!=null&&captcha.getError()==0){
                getSharedPreferences("info",MODE_PRIVATE).edit().putString("phoneNum",phoneNum).commit();

            }

        }

        @Override
        public void onError(String result) {

        }
    };

}
