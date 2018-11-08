package com.shushan.thomework101.HttpHelper.service.entity.wallet;

import java.util.ArrayList;

public class WalletData {
    private UserInfo userinfo;
    private ArrayList<Detail> detail;

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }

    public ArrayList<Detail> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<Detail> detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "WalletData{" +
                "userinfo=" + userinfo +
                ", detail=" + detail +
                '}';
    }
}
