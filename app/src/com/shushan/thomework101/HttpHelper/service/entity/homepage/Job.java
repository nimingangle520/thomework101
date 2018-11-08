package com.shushan.thomework101.HttpHelper.service.entity.homepage;

import java.util.Arrays;

public class Job {
    private int job_id;
    private String[] images;
    private int start_time;
    private String grade;
    private String subject;
    private int is_receive;
    private int create_time;
    private int price;
    private int label;
    private int yuyue;

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getIs_receive() {
        return is_receive;
    }

    public void setIs_receive(int is_receive) {
        this.is_receive = is_receive;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getYuyue() {
        return yuyue;
    }

    public void setYuyue(int yuyue) {
        this.yuyue = yuyue;
    }

    @Override
    public String toString() {
        return "Job{" +
                "job_id=" + job_id +
                ", images=" + Arrays.toString(images) +
                ", start_time=" + start_time +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", is_receive=" + is_receive +
                ", create_time=" + create_time +
                ", price=" + price +
                ", label=" + label +
                ", yuyue=" + yuyue +
                '}';
    }
}
