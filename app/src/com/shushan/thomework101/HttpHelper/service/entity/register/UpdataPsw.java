package com.shushan.thomework101.HttpHelper.service.entity.register;

import java.util.Arrays;

public class UpdataPsw  {
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
        return "UpdataPsw{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
