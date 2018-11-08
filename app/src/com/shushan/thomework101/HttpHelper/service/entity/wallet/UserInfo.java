package com.shushan.thomework101.HttpHelper.service.entity.wallet;

public class UserInfo {
    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "money='" + money + '\'' +
                '}';
    }
}
