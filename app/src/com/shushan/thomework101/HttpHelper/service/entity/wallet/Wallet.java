package com.shushan.thomework101.HttpHelper.service.entity.wallet;

public class Wallet {
    private int error;
    private String msg;
    private WalletData data;

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

    public WalletData getData() {
        return data;
    }

    public void setData(WalletData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
