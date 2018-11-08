package com.shushan.thomework101.login;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shushan.thomework101.Constants;
import com.shushan.thomework101.HttpHelper.service.entity.register.Register;
import com.shushan.thomework101.HttpHelper.service.entity.register.UpdataPsw;
import com.shushan.thomework101.HttpHelper.service.presenter.RegisterPresenter;
import com.shushan.thomework101.HttpHelper.service.presenter.UpdataPswPresenter;
import com.shushan.thomework101.HttpHelper.service.view.RegisterView;
import com.shushan.thomework101.HttpHelper.service.view.UpdataPswView;
import com.shushan.thomework101.R;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;

public class SetPswActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.btn_set_next)
    Button btn_set_next;
    @Bind(R.id.et_set_psw)
    EditText et_set_psw;
    @Bind(R.id.et_set_psw_again)
    EditText et_set_psw_again;
    private String code;
    private SharedPreferences sharedPreferences;
    private String phoneNum;
    private String psw;
    private String confirmPsw;
    private Map<String, String> requestMap;
    private String imei;
    private RegisterPresenter registerPresenter;
    private boolean isUpdataPsw;
    private UpdataPswPresenter updataPswPresenter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_set_psw);
        changeStatusBarTextImgColor(true);
    }

    @Override
    protected void initViews() {
        requestMap = new HashMap<>();
        registerPresenter = new RegisterPresenter(this);
        registerPresenter.onCreate(Constants.BASE_URL);
        registerPresenter.attachView(registerView);
        updataPswPresenter = new UpdataPswPresenter(this);
        updataPswPresenter.onCreate(Constants.BASE_URL);
        updataPswPresenter.attachView(updataPswView);

    }

    @Override
    protected void initEvents() {
        btn_set_next.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            code = intent.getStringExtra("code");
        }
        SharedPreferences sharedPreferences = getSharedPreferences("info", MODE_PRIVATE);
        phoneNum = sharedPreferences.getString("phoneNum", "");
        isUpdataPsw = sharedPreferences.getBoolean("isUpdataPsw",false);
    }

    private RegisterView registerView = new RegisterView() {
        @Override
        public void onSuccess(Register register) {
            if(register!=null&&register.getError()==0){
                SharedPreferences sharedPreferences=getSharedPreferences("info",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putInt("tid",register.getData().getTid());
                editor.putString("token",register.getData().getToken());
                editor.putInt("online",register.getData().getOnline());
                editor.putString("third_id",register.getData().getThird_id());
                editor.putString("third_token",register.getData().getThird_token());
                editor.commit();
                startActivitys(SetPswActivity.this, SetIdentityActivity.class);
            }

        }

        @Override
        public void onError(String result) {

        }
    };
    private UpdataPswView updataPswView=new UpdataPswView() {
        @Override
        public void onSuccess(UpdataPsw updataPsw) {
            if(updataPsw!=null&&updataPsw.getError()==0){
                startActivitys(SetPswActivity.this, SetIdentityActivity.class);
            }
            startActivitys(SetPswActivity.this, SetIdentityActivity.class);
        }

        @Override
        public void onError(String result) {

        }
    };
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_set_next:
                psw = et_set_psw.getText().toString().trim();
                confirmPsw = et_set_psw_again.getText().toString().trim();
                if (isUpdataPsw == false) {

                    if (!TextUtils.isEmpty(phoneNum)
                            && !TextUtils.isEmpty(code)
                            && !TextUtils.isEmpty(psw)
                            && !TextUtils.isEmpty(confirmPsw)
                            && psw.equals(confirmPsw)
                            && psw.length() >= 6
                            && confirmPsw.length() >= 6
                            ) {

                        requestMap.put("mobile", phoneNum);
                        requestMap.put("code", code);
                        requestMap.put("password", Utils.stringToMD5(psw));
                        requestMap.put("deviceId", getIMEI(SetPswActivity.this));
                        requestMap.put("platform", "android");

                        LogUtils.d("mobile:" + phoneNum +
                                "\n" + "password:" + Utils.stringToMD5(psw) +
                                "\n" + "code:" + code +
                                "\n" + "deviceId:" + getIMEI(SetPswActivity.this) +
                                "\n" + "platform:" + "android");

                        registerPresenter.getCallRegisterMsg(requestMap);

                        break;
                    }

                }else{
                    if (!TextUtils.isEmpty(phoneNum)
                            && !TextUtils.isEmpty(code)
                            && !TextUtils.isEmpty(psw)
                            && !TextUtils.isEmpty(confirmPsw)
                            && psw.equals(confirmPsw)
                            && psw.length() >= 6
                            && confirmPsw.length() >= 6
                            ) {
                        updataPswPresenter.getUpdataPswMsg(phoneNum,code,Utils.stringToMD5(psw));
                    }
                }
        }

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
