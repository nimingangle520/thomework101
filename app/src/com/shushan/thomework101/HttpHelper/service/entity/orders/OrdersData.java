package com.shushan.thomework101.HttpHelper.service.entity.orders;

import java.util.Arrays;

public class OrdersData {

    private float price;
    private String oid;
    private int stu_id;
    private int status;
    private int create_time;
    private int label;
    private int jobid;
    private String[] job_pic;
    private String grade;
    private String subject;
    private String username;
    private String trait;
    private int userid;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCreate_time() {
        return create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public String[] getJob_pic() {
        return job_pic;
    }

    public void setJob_pic(String[] job_pic) {
        this.job_pic = job_pic;
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


    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public int getStu_id() {
        return stu_id;
    }

    public void setStu_id(int stu_id) {
        this.stu_id = stu_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "OrdersData{" +
                "price=" + price +
                ", oid='" + oid + '\'' +
                ", stu_id=" + stu_id +
                ", status=" + status +
                ", create_time=" + create_time +
                ", label=" + label +
                ", jobid=" + jobid +
                ", job_pic=" + Arrays.toString(job_pic) +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", username='" + username + '\'' +
                ", trait='" + trait + '\'' +
                ", userid=" + userid +
                '}';
    }
}
