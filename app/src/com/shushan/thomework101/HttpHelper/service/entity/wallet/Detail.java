package com.shushan.thomework101.HttpHelper.service.entity.wallet;

public class Detail {
    private String income_no;
    private double income;
    private long income_time;
    private int label;

    public String getIncome_no() {
        return income_no;
    }

    public void setIncome_no(String income_no) {
        this.income_no = income_no;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public long getIncome_time() {
        return income_time;
    }

    public void setIncome_time(long income_time) {
        this.income_time = income_time;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "income_no='" + income_no + '\'' +
                ", income='" + income + '\'' +
                ", income_time='" + income_time + '\'' +
                ", label=" + label +
                '}';
    }
}
