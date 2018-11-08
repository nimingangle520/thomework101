package com.shushan.thomework101.HttpHelper.service;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface RetrofitService {

    @FormUrlEncoded
    @POST("admin/code/get")
    Call<ResponseBody> getCaptchaMsg(@Field("mobile") String mobile);

    @FormUrlEncoded
    @POST("/teacher/user/register")
    Call<ResponseBody> getCallRegisterMsg(@FieldMap Map<String, String> requests);

    @FormUrlEncoded
    @POST("admin/code/check")
    Call<ResponseBody> getVerifyCaptchaMsg(@Field("mobile") String mobile, @Field("code") String code);

    @FormUrlEncoded
    @POST("teacher/user/login")
    Call<ResponseBody> getLoginMsg(@FieldMap Map<String, String> requests);

    @FormUrlEncoded
    @POST("teacher/user/setpwd")
    Call<ResponseBody> getUpdataPswMsg(@Field("mobile") String mobile,@Field("code") String code,@Field("password") String password);

    @FormUrlEncoded
    @POST("teacher/work/online")
    Call<ResponseBody> getOnlineMsg(@Field("tid") String tid,@Field("token") String token,@Field("online") String online);

    @FormUrlEncoded
    @POST("teacher")
    Call<ResponseBody> getHomepageMsg(@FieldMap Map<String, String> requests);

    @FormUrlEncoded
    @POST("/teacher/work/find")
    Call<ResponseBody> getHomeworkDetailsMsg(@Field("tid") String tid,@Field("token") String token,@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("teacher/work/check")
    Call<ResponseBody> getGoChecksMsg(@Field("tid") String tid,@Field("token") String token,@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("teacher/order")
    Call<ResponseBody> getOrdersMsg(@FieldMap Map<String, String> requests);

    @FormUrlEncoded
    @POST("teacher/work/accept")
    Call<ResponseBody> getGoAcceptMsg(@Field("tid") String tid,@Field("token") String token,@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("teacher/work/guide")
    Call<ResponseBody> getGoTutorshipMsg(@Field("tid") String tid,@Field("token") String token,@Field("jobid") String jobid);

    @FormUrlEncoded
    @POST("teacher/order/detail")
    Call<ResponseBody> getOrdersDetailsMsg(@Field("tid") String tid,@Field("token") String token,@Field("oid") String oid,@Field("label") String label);

    @FormUrlEncoded
    @POST("teacher/wallet/mine")
    Call<ResponseBody> getMineWalletMsg(@Field("token") String token);

    @FormUrlEncoded
    @POST("/teacher/wallet/detail")
    Call<ResponseBody> getWalletDetailsMsg(@Field("token") String token,@Field("label") String label,@Field("page") String page,@Field("date") String date);






    @FormUrlEncoded
    @POST("student/job/check")
    Call<ResponseBody> getJobCheckMsg(@Field("userid") String userid, @Field("token") String token);

    @FormUrlEncoded
    @POST("student/upload")
    Call<ResponseBody> getUploadMsg(@Field("pic") String pic, @Field("t") String t, @Field("dir") String dir);

    @FormUrlEncoded
    @POST("student/job/publish")
    Call<ResponseBody> getJobPublishMsg(@Field("userid") String userid, @Field("images") String images, @Field("label") int label,
                                        @Field("grade") String grade, @Field("subject") String subject, @Field("price") float price,
                                        @Field("start_time") String start_time, @Field("token") String token);

    @POST("student/job/grade")
    Call<ResponseBody> getGradeMsg();

    @POST("student/job/subject")
    Call<ResponseBody> getSubjectMsg();

    @FormUrlEncoded
    @POST("student/order/pay")
    Call<ResponseBody> getPayMsg(@FieldMap Map<String, String> requests);
}
