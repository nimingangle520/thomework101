package com.shushan.thomework101;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.HttpHelper.service.entity.register.Login;
import com.shushan.thomework101.HttpHelper.service.presenter.LoginPresenter;
import com.shushan.thomework101.HttpHelper.service.view.LoginView;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.homepage.HomepageActivity;
import com.shushan.thomework101.login.RegistActivity;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.tv_login_register)
    TextView tv_login_register;
    @Bind(R.id.tv_login_forget_psw)
    TextView tv_login_forget_psw;
    @Bind(R.id.iv_login_agree)
    ImageView iv_login_agree;
    @Bind(R.id.et_login_phone)
    EditText et_login_phone;
    @Bind(R.id.et_login_psw)
    EditText et_login_psw;
    @Bind(R.id.btn_login)
    Button btn_login;
    private boolean isAgree=false;
    private LoginPresenter loginPresenter;
    private Map<String, String> requestMap;
    private String imei;
    private boolean isUpdataPsw=false;
    private String phoneNum;
    private String psw;
    private SharedPreferences sharedPreferences;
    @Override
    protected void initContentView() {

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        et_login_phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        et_login_psw.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        loginPresenter = new LoginPresenter(this);
        loginPresenter.onCreate(Constants.BASE_URL);
        loginPresenter.attachView(loginView);
    }

    @Override
    protected void initEvents() {
        tv_login_register.setOnClickListener(this);
        tv_login_forget_psw.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        iv_login_agree.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        requestMap = new HashMap<>();
        sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        phoneNum= sharedPreferences.getString("phoneNum","");
        if(!TextUtils.isEmpty(phoneNum)){
            et_login_phone.setText(phoneNum);
        }else{
            et_login_phone.setHint(getResources().getString(R.string.login_please_input_phone_number));
        }
    }

    private LoginView loginView = new LoginView() {
        @Override
        public void onSuccess(Login login) {
            if (login != null && !login.equals("") && login.getError() == 0) {
                SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                sharedPreferences.edit().putString("token", login.getData().getToken())
                        .putInt("tid",login.getData().getTid())
                        .putString("phoneNum",et_login_phone.getText().toString())
                        .putString("third_token",login.getData().getThird_token())
                        .putString("third_id",login.getData().getThird_id())
                        .apply();
                BaseActivity.startActivitys(MainActivity.this, HomepageActivity.class);
            }
        }

        @Override
        public void onError(String result) {

        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_login_register:
                isUpdataPsw=false;
                SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
                sharedPreferences.edit().putBoolean("isUpdataPsw",isUpdataPsw).commit();
                startActivitys(this, RegistActivity.class);
                break;
            case R.id.tv_login_forget_psw:
                isUpdataPsw=true;
                SharedPreferences spf_psw = getSharedPreferences("info", MODE_PRIVATE);
                spf_psw.edit().putBoolean("isUpdataPsw",isUpdataPsw).commit();
                startActivitys(this, RegistActivity.class);
                break;
            case R.id.iv_login_agree:
                if(!isAgree){
                    isAgree=true;
                    iv_login_agree.setImageResource(R.drawable.login_agreement_agree);
                }else{
                    iv_login_agree.setImageResource(R.drawable.login_agreement);
                    isAgree=false;
                }
                break;
            case R.id.btn_login:
                phoneNum = et_login_phone.getText().toString().trim();
                psw = et_login_psw.getText().toString().trim();
                if(!TextUtils.isEmpty(phoneNum)&&!TextUtils.isEmpty(psw)&& psw.length()>=6&&isAgree){
                    requestMap.put("mobile", phoneNum);
                    requestMap.put("password", Utils.stringToMD5(psw));
                    requestMap.put("deviceId",getIMEI(MainActivity.this));
                    requestMap.put("platform","android");

                    LogUtils.d("mobile:" + phoneNum +
                            "\n" + "password:" + Utils.stringToMD5(psw) +
                            "\n" + "deviceId:" + getIMEI(MainActivity.this) +
                            "\n" + "platform:" + "android");
                    loginPresenter.getLoginMsg(requestMap);
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
            et_login_phone.setHint(phoneNum);
        }else{
            et_login_phone.setText(phoneNum);
        }
        et_login_psw.setText("");
    }
    /**
     * 获取手机IMEI
     *
     * @param context
     * @return
     */
    public  String getIMEI(Context context) {
        try {
            //实例化TelephonyManager对象
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            //获取IMEI号
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && this.checkSelfPermission(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                this.requestPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                }, 200);

            }else{
                imei = telephonyManager.getDeviceId();
            }
            //在次做个验证，也不是什么时候都能获取到的啊
            if (imei == null) {
                imei = "";
            }
            return imei;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }
}
