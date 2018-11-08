package com.shushan.thomework101.HttpHelper.service.entity.homepage;

import java.util.ArrayList;

public class HomepageTutorshipData {
    private ArrayList<HomepageData> receive;
    private ArrayList<HomepageData> guide_now;
    private ArrayList<HomepageData> guide_pre;


    public ArrayList<HomepageData> getReceive() {
        return receive;
    }

    public void setReceive(ArrayList<HomepageData> receive) {
        this.receive = receive;
    }

    public ArrayList<HomepageData> getGuide_now() {
        return guide_now;
    }

    public void setGuide_now(ArrayList<HomepageData> guide_now) {
        this.guide_now = guide_now;
    }

    public ArrayList<HomepageData> getGuide_pre() {
        return guide_pre;
    }

    public void setGuide_pre(ArrayList<HomepageData> guide_pre) {
        this.guide_pre = guide_pre;
    }

    @Override
    public String toString() {
        return "HomepageTutorshipData{" +
                "receive=" + receive +
                ", guide_now=" + guide_now +
                ", guide_pre=" + guide_pre +
                '}';
    }
}
