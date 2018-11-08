package com.shushan.thomework101.HttpHelper.service;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitHelper {

    private Context mCntext;
    Gson gson = new GsonBuilder()
            .setLenient()
            .create();
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    private  OkHttpClient client=new OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .build();


    public static RetrofitHelper getInstance(Context context, String baseUrl){
        if (instance == null){
            instance = new RetrofitHelper(context,baseUrl);
        }
        return instance;
    }
    private RetrofitHelper(Context mContext, String baseUrl){
        mCntext = mContext;
        init(baseUrl);
    }

    private void init(String baseUrl) {
        resetApp(baseUrl);
    }

    private void resetApp(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public RetrofitService getServer(){
        return mRetrofit.create(RetrofitService.class);
    }
}
