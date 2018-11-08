package com.shushan.thomework101.bean;

import java.util.ArrayList;

public class MyOrdersBean {
    private ArrayList<Integer> homeworkIcons;
    private String time;
    private int studIcon;
    private String stuName;
    private String grade;
    private String money;
    private String state;

    public ArrayList<Integer> getHomeworkIcons() {
        return homeworkIcons;
    }

    public void setHomeworkIcons(ArrayList<Integer> homeworkIcons) {
        this.homeworkIcons = homeworkIcons;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStudIcon() {
        return studIcon;
    }

    public void setStudIcon(int studIcon) {
        this.studIcon = studIcon;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "MyOrdersBean{" +
                "homeworkIcons=" + homeworkIcons +
                ", time='" + time + '\'' +
                ", studIcon=" + studIcon +
                ", stuName='" + stuName + '\'' +
                ", grade='" + grade + '\'' +
                ", money='" + money + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
