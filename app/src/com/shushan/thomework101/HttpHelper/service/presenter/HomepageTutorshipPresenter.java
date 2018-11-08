package com.shushan.thomework101.HttpHelper.service.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageTutorship;
import com.shushan.thomework101.HttpHelper.service.manager.DataManager;
import com.shushan.thomework101.HttpHelper.service.view.HomepageTutorshipView;
import com.shushan.thomework101.HttpHelper.service.view.View;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;

public class HomepageTutorshipPresenter implements Presenter{

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private HomepageTutorshipView homepageTutorshipView;
    private HomepageTutorship mHomepageTutorship;

    public HomepageTutorshipPresenter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public void onCreate(String baseUrl) {
        manager = new DataManager(mContext,baseUrl);
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mCompositeSubscription.hasSubscriptions()){
            mCompositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(View view) {
        homepageTutorshipView= (HomepageTutorshipView) view;

    }

    @Override
    public void attachIncomingIntent(Intent intetn) {
    }

    public void getHomepageMsg(Map<String,String> requests) {
        manager.getHomepageMsg(requests).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.body() != null) {
                        String str = response.body().string().trim();
                        String newStr = Utils.UStr_2_Str(str);
                        LogUtils.d(newStr);
                        Gson gson = new Gson();
                            mHomepageTutorship=gson.fromJson(newStr,HomepageTutorship.class);
                            if(homepageTutorshipView!=null){
                                homepageTutorshipView.onSuccess(mHomepageTutorship);
                        }
                    } else {
                        LogUtils.d("is null");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if(t!=null&& !TextUtils.isEmpty(t.getMessage())){
                    t.printStackTrace();
                    LogUtils.e(t.getMessage());

                    homepageTutorshipView.onError(t.getMessage());

                }
            }
        });
    }
}
