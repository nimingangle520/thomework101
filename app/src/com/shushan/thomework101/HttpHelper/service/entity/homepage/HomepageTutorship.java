package com.shushan.thomework101.HttpHelper.service.entity.homepage;

public class HomepageTutorship {
    int error;
    String msg;
    HomepageTutorshipData data;

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

    public HomepageTutorshipData getData() {
        return data;
    }

    public void setData(HomepageTutorshipData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "HomepageTurorship{" +
                "error=" + error +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
