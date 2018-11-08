package com.shushan.thomework101.bean;

import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;

import java.util.ArrayList;

public class HomeworkOrdersList {
    private int type;
    private ArrayList<HomepageData> homepageData;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ArrayList<HomepageData> getHomepageData() {
        return homepageData;
    }

    public void setHomepageData(ArrayList<HomepageData> homepageData) {
        this.homepageData = homepageData;
    }

    @Override
    public String toString() {
        return "HomeworkOrdersList{" +
                "type=" + type +
                ", homepageData=" + homepageData +
                '}';
    }
}
