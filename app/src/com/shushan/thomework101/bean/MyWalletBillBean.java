package com.shushan.thomework101.bean;

public class MyWalletBillBean {
    private int billIcon;
    private String billType;
    private String billTime;
    private String billMoney;

    public int getBillIcon() {
        return billIcon;
    }

    public void setBillIcon(int billIcon) {
        this.billIcon = billIcon;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getBillMoney() {
        return billMoney;
    }

    public void setBillMoney(String billMoney) {
        this.billMoney = billMoney;
    }

    @Override
    public String toString() {
        return "MyWalletBillBean{" +
                "billIcon=" + billIcon +
                ", billType='" + billType + '\'' +
                ", billTime='" + billTime + '\'' +
                ", billMoney='" + billMoney + '\'' +
                '}';
    }
}
