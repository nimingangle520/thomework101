package com.shushan.thomework101.HttpHelper.service.entity.homepage;

import java.io.Serializable;
import java.util.Arrays;

public class HomepageData implements Serializable{

    private int job_id;
    private int stu_id;
    private String [] job_pic;
    private int label;
    private long start_time;
    private float price;
    private int is_receive;
    private String grade;
    private String subject;
    private String username;
    private String trait;
    private String third_id;

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String[] getJob_pic() {
        return job_pic;
    }

    public void setJob_pic(String[] job_pic) {
        this.job_pic = job_pic;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public long getStart_time() {
        return start_time;
    }

    public void setStart_time(long start_time) {
        this.start_time = start_time;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIs_receive() {
        return is_receive;
    }

    public void setIs_receive(int is_receive) {
        this.is_receive = is_receive;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public String getThird_id() {
        return third_id;
    }

    public void setThird_id(String third_id) {
        this.third_id = third_id;
    }

    @Override
    public String toString() {
        return "HomepageData{" +
                "job_id=" + job_id +
                ", stu_id=" + stu_id +
                ", job_pic=" + Arrays.toString(job_pic) +
                ", label=" + label +
                ", start_time=" + start_time +
                ", price=" + price +
                ", is_receive=" + is_receive +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", username='" + username + '\'' +
                ", trait='" + trait + '\'' +
                ", third_id='" + third_id + '\'' +
                '}';
    }
}
