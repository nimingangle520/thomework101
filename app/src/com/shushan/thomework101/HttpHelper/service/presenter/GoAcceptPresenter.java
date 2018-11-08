package com.shushan.thomework101.HttpHelper.service.presenter;

import android.content.Context;
import android.content.Intent;

import com.google.gson.Gson;
import com.shushan.thomework101.HttpHelper.service.entity.orders.GoAccept;
import com.shushan.thomework101.HttpHelper.service.manager.DataManager;
import com.shushan.thomework101.HttpHelper.service.view.GoAcceptView;
import com.shushan.thomework101.HttpHelper.service.view.View;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;

public class GoAcceptPresenter implements Presenter{
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private GoAcceptView goAcceptView;
    private GoAccept mGoAccept;

    public GoAcceptPresenter(Context mContext){
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
        goAcceptView = (GoAcceptView) view;

    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void getGoAcceptMsg(String tid,String token,String jobid) {
        manager.getGoAcceptMsg(tid,token,jobid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.body() != null) {
                        String str = response.body().string().trim();
                        String newStr = Utils.UStr_2_Str(str);
                        LogUtils.d(newStr);
                        Gson gson = new Gson();
                        mGoAccept = gson.fromJson(newStr, GoAccept.class);

                        if (goAcceptView != null) {
                            goAcceptView.onSuccess(mGoAccept);
                        }
                    } else {
                        LogUtils.d("is null");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                LogUtils.e(t.getMessage());
                goAcceptView.onError(t.getMessage());
            }
        });
    }
}
