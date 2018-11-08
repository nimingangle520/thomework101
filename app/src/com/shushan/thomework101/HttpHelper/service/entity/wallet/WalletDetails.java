package com.shushan.thomework101.HttpHelper.service.entity.wallet;

public class WalletDetails {
    private int error;
    private String msg;
    private WalletDetailsData data;

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

    public WalletDetailsData getData() {
        return data;
    }

    public void setData(WalletDetailsData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WalletDetails{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
