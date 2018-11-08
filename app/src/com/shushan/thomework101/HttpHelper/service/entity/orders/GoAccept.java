package com.shushan.thomework101.HttpHelper.service.entity.orders;

import java.util.Arrays;

public class GoAccept {

    int error;
    String msg;
    String [] data;

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

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "GoAccept{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
