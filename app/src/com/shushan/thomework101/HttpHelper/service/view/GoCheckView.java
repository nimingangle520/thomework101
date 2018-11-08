package com.shushan.thomework101.HttpHelper.service.view;

import com.shushan.thomework101.HttpHelper.service.entity.orders.GoCheck;

public interface GoCheckView extends View{
    void onSuccess(GoCheck goCheck);
    void onError(String result);
}
