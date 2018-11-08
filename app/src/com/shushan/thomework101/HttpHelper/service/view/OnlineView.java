package com.shushan.thomework101.HttpHelper.service.view;

import com.shushan.thomework101.HttpHelper.service.entity.register.Online;

public interface OnlineView extends View {
    void onSuccess(Online online);
    void onError(String result);
}
