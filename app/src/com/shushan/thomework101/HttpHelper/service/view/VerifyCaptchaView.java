package com.shushan.thomework101.HttpHelper.service.view;


import com.shushan.thomework101.HttpHelper.service.entity.register.VerifyCaptcha;

public interface VerifyCaptchaView extends View{
    void onSuccess(VerifyCaptcha verifyCaptcha);
    void onError(String result);

}
