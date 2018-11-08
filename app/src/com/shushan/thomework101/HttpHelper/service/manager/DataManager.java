package com.shushan.thomework101.HttpHelper.service.manager;

import android.content.Context;

import com.shushan.thomework101.HttpHelper.service.RetrofitHelper;
import com.shushan.thomework101.HttpHelper.service.RetrofitService;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;


public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context, String baseUrl){
        this.mRetrofitService = RetrofitHelper.getInstance(context,baseUrl).getServer();
    }

    public Call<ResponseBody> getCaptchaMsg( String mobile){
        return  mRetrofitService.getCaptchaMsg(mobile);
    }


    public Call<ResponseBody> getCallRegisterMsg(Map<String,String> requests){
        return  mRetrofitService.getCallRegisterMsg(requests);
    }

    public Call<ResponseBody> getVerifyCaptchaMsg(String mobile, String code){
        return  mRetrofitService.getVerifyCaptchaMsg(mobile,code);
    }

    public Call<ResponseBody> getLoginMsg(Map<String,String> requests){
        return  mRetrofitService.getLoginMsg(requests);
    }

    public Call<ResponseBody> getUpdataPswMsg(String mobile,String code,String password){
        return  mRetrofitService.getUpdataPswMsg(mobile,code,password);
    }

    public Call<ResponseBody> getOnlineMsg(String tid,String token,String online){
        return  mRetrofitService.getOnlineMsg(tid,token,online);
    }

    public Call<ResponseBody> getHomepageMsg(Map<String,String> requests){
        return  mRetrofitService.getHomepageMsg(requests);
    }

    public Call<ResponseBody> getHomeworkDetailsMsg(String tid,String token,String jobid){
        return  mRetrofitService.getHomeworkDetailsMsg(tid,token,jobid);
    }

    public Call<ResponseBody> getGoChecksMsg(String tid,String token,String jobid){
        return  mRetrofitService.getGoChecksMsg(tid,token,jobid);
    }

    public Call<ResponseBody> getOrdersMsg(Map<String,String> requests){
        return  mRetrofitService.getOrdersMsg(requests);
    }

    public Call<ResponseBody> getGoAcceptMsg(String tid,String token,String jobid){
        return  mRetrofitService.getGoAcceptMsg(tid,token,jobid);
    }

    public Call<ResponseBody> getGoTutorshipMsg(String tid,String token,String jobid){
        return  mRetrofitService.getGoTutorshipMsg(tid,token,jobid);
    }

    public Call<ResponseBody> getOrdersDetailsMsg(String tid,String token,String oid,String label){
        return  mRetrofitService.getOrdersDetailsMsg(tid,token,oid,label);
    }

    public Call<ResponseBody> getMineWalletMsg(String token){
        return  mRetrofitService.getMineWalletMsg(token);
    }

    public Call<ResponseBody> getWalletDetailsMsg(String token,String label,String page,String date){
        return  mRetrofitService.getWalletDetailsMsg(token,label,page,date);
    }




    public Call<ResponseBody> getUploadMsg(String pic, String t, String dir){
        return  mRetrofitService.getUploadMsg(pic,t,dir);
    }

    public Call<ResponseBody> getGradeMsg(){
        return  mRetrofitService.getGradeMsg();
    }

    public Call<ResponseBody> getSubjectMsg(){
        return  mRetrofitService.getSubjectMsg();
    }

}
