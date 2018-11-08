package com.shushan.thomework101.HttpHelper.service.entity.homework;

public class HomeworkDetails {
    int error;
    String msg;
    HomeworkDetailsData data;

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

    public HomeworkDetailsData getData() {
        return data;
    }

    public void setData(HomeworkDetailsData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HomeworkDetails{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
