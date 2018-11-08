package com.shushan.thomework101.HttpHelper.service.entity.homework;

import java.util.Arrays;

public class HomeworkDetailsData {

    private int job_id;
    private String [] job_pic;
    private float price;
    private int label;
    private String username;
    private String trait;
    private String grade;
    private String subject;
    private String third_id;
    private String oid;

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }

    public String[] getJob_pic() {
        return job_pic;
    }

    public void setJob_pic(String[] job_pic) {
        this.job_pic = job_pic;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
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

    public String getThird_id() {
        return third_id;
    }

    public void setThird_id(String third_id) {
        this.third_id = third_id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "HomeworkDetailsData{" +
                "job_id=" + job_id +
                ", job_pic=" + Arrays.toString(job_pic) +
                ", price=" + price +
                ", label=" + label +
                ", username='" + username + '\'' +
                ", trait='" + trait + '\'' +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", third_id='" + third_id + '\'' +
                ", oid='" + oid + '\'' +
                '}';
    }
}
