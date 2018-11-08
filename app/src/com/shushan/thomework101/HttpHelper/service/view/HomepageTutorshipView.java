package com.shushan.thomework101.HttpHelper.service.view;

import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageTutorship;

public interface HomepageTutorshipView extends View{
    void onSuccess(HomepageTutorship homepageTutorship);
    void onError(String result);
}
