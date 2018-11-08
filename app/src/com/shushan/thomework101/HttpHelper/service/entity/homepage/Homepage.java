package com.shushan.thomework101.HttpHelper.service.entity.homepage;

import java.util.ArrayList;

public class Homepage {
    int error;
    String msg;
    ArrayList<HomepageData> data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ArrayList<HomepageData> getData() {
        return data;
    }

    public void setData(ArrayList<HomepageData> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Homepage{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
