package com.shushan.thomework101.HttpHelper.service.view;

import com.shushan.thomework101.HttpHelper.service.entity.homework.HomeworkDetails;

public interface HomeworkDetailsView extends View {
    void onSuccess(HomeworkDetails homeworkDetails);
    void onError(String result);
}
