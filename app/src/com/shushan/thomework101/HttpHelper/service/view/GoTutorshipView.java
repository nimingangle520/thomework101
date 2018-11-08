package com.shushan.thomework101.HttpHelper.service.view;

import com.shushan.thomework101.HttpHelper.service.entity.orders.GoTutorship;

public interface GoTutorshipView extends View {
    void onSuccess(GoTutorship goTutorship);
    void onError(String result);
}
