package com.shushan.thomework101.HttpHelper.service.view;

import com.shushan.thomework101.HttpHelper.service.entity.orders.GoAccept;

public interface GoAcceptView extends View {
    void onSuccess(GoAccept goAccept);
    void onError(String result);
}
