package com.shushan.thomework101.HttpHelper.service.entity.register;

public class Data {
    private int tid;
    private String token;
    private int online;
    private String third_id;
    private String third_token;

    public String getThird_token() {
        return third_token;
    }

    public void setThird_token(String third_token) {
        this.third_token = third_token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getThird_id() {
        return third_id;
    }

    public void setThird_id(String third_id) {
        this.third_id = third_id;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    @Override
    public String toString() {
        return "Data{" +
                "tid=" + tid +
                ", token='" + token + '\'' +
                ", online=" + online +
                ", third_id='" + third_id + '\'' +
                ", third_token='" + third_token + '\'' +
                '}';
    }
}
