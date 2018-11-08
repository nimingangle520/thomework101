package com.shushan.thomework101.HttpHelper.service.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.shushan.thomework101.HttpHelper.service.entity.wallet.WalletDetails;
import com.shushan.thomework101.HttpHelper.service.manager.DataManager;
import com.shushan.thomework101.HttpHelper.service.view.View;
import com.shushan.thomework101.HttpHelper.service.view.WalletDetailsView;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;

public class WalletDetailsPresenter implements Presenter{

    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private WalletDetailsView walletDetailsView;
    private WalletDetails mWalletDetails;

    public WalletDetailsPresenter(Context mContext){
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
        walletDetailsView = (WalletDetailsView) view;

    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void getWalletDetailsMsg(String token,String label,String page,String date) {
        manager.getWalletDetailsMsg(token,label,page,date).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.body() != null) {
                        String str = response.body().string().trim();
                        String newStr = Utils.UStr_2_Str(str);
                        LogUtils.d(newStr);
                        Gson gson = new Gson();
                        mWalletDetails = gson.fromJson(newStr, WalletDetails.class);

                        if (walletDetailsView != null) {
                            walletDetailsView.onSuccess(mWalletDetails);
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
                if(t!=null&&!TextUtils.isEmpty(t.getMessage())){
                    t.printStackTrace();
                    LogUtils.e(t.getMessage());
                    walletDetailsView.onError(t.getMessage());

                }
            }
        });
    }
}
