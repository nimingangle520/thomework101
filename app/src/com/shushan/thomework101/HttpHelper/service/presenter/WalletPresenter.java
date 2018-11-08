package com.shushan.thomework101.HttpHelper.service.presenter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.shushan.thomework101.HttpHelper.service.entity.wallet.Wallet;
import com.shushan.thomework101.HttpHelper.service.manager.DataManager;
import com.shushan.thomework101.HttpHelper.service.view.View;
import com.shushan.thomework101.HttpHelper.service.view.WalletView;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;

public class WalletPresenter implements Presenter{
    private DataManager manager;
    private CompositeSubscription mCompositeSubscription;
    private Context mContext;
    private WalletView walletView;
    private Wallet mWallet;

    public WalletPresenter(Context mContext){
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
        walletView = (WalletView) view;

    }

    @Override
    public void attachIncomingIntent(Intent intent) {
    }

    public void getMineWalletMsg(String token) {
        manager.getMineWalletMsg(token).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    if (response.body() != null) {
                        String str = response.body().string().trim();
                        String newStr = Utils.UStr_2_Str(str);
                        LogUtils.d(newStr);
                        Gson gson = new Gson();
                        mWallet = gson.fromJson(newStr, Wallet.class);

                        if (walletView != null) {
                            walletView.onSuccess(mWallet);
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
                    walletView.onError(t.getMessage());

                }
            }
        });
    }
}
