package com.shushan.thomework101.HttpHelper.service.view;


import com.shushan.thomework101.HttpHelper.service.entity.homepage.Homepage;

public interface HomepageView extends View{
    void onSuccess(Homepage homepage);
    void onError(String result);
}
