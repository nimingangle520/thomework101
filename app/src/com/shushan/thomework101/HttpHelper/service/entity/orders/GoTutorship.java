package com.shushan.thomework101.HttpHelper.service.entity.orders;

public class GoTutorship {
    int error;
    String msg;
    GoTutorshipData data;

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

    public GoTutorshipData getData() {
        return data;
    }

    public void setData(GoTutorshipData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoTutorship{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
