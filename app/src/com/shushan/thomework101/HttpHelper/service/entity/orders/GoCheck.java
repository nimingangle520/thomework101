package com.shushan.thomework101.HttpHelper.service.entity.orders;

public class GoCheck {
    int error;
    String msg;
    GoCheckData data;

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

    public GoCheckData getData() {
        return data;
    }

    public void setData(GoCheckData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoCheck{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
