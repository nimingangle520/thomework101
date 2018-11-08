package com.shushan.thomework101.HttpHelper.service.view;

import com.shushan.thomework101.HttpHelper.service.entity.register.UpdataPsw;

public interface UpdataPswView extends View {
    void onSuccess(UpdataPsw updataPsw);
    void onError(String result);
}
