package com.shushan.thomework101.HttpHelper.service.entity.wallet;

import java.util.ArrayList;

public class WalletDetailsData {
    private UserInfo userinfo;
    private ArrayList<Detail> bill;

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public ArrayList<Detail> getBill() {
        return bill;
    }

    public void setBill(ArrayList<Detail> bill) {
        this.bill = bill;
    }

    @Override
    public String toString() {
        return "WalletDetailsData{" +
                "userinfo=" + userinfo +
                ", bill=" + bill +
                '}';
    }
}
