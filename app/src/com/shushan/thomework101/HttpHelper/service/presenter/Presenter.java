package com.shushan.thomework101.HttpHelper.service.presenter;


import android.content.Intent;

import com.shushan.thomework101.HttpHelper.service.view.View;


public interface Presenter {
    void onCreate(String baseUrl);

    void onStart();

    void onStop();

    void pause();

    void attachView(View view);

    void attachIncomingIntent(Intent intent);
}
