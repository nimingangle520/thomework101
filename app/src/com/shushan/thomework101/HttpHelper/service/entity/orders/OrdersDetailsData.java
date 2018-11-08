package com.shushan.thomework101.HttpHelper.service.entity.orders;

import java.util.Arrays;

public class OrdersDetailsData {
    private String oid;
    private long create_time;
    private int ord_status;//订单状态0辅导中1待付款2已付款3已取消
    private String username;
    private String trait;
    private int userid;
    private String third_id;
    private String grade;
    private String subject;
    private String[] pics;
    private String cover;
    private String video_url;
    private float price;
    private int jobid;
    private long job_time;
    private String[] job_pic;
    private float pay_money;
    private int cate;//支付类别1支付宝2微信3余额
    private long pay_time;
    private int label;
    private Comment comment;
    private Complain complain;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public void setCreate_time(int create_time) {
        this.create_time = create_time;
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

    public String[] getPics() {
        return pics;
    }

    public void setPics(String[] pics) {
        this.pics = pics;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getJobid() {
        return jobid;
    }

    public void setJobid(int jobid) {
        this.jobid = jobid;
    }

    public long getJob_time() {
        return job_time;
    }

    public void setJob_time(long job_time) {
        this.job_time = job_time;
    }

    public String[] getJob_pic() {
        return job_pic;
    }

    public void setJob_pic(String[] job_pic) {
        this.job_pic = job_pic;
    }

    public float getPay_money() {
        return pay_money;
    }

    public void setPay_money(float pay_money) {
        this.pay_money = pay_money;
    }

    public int getCate() {
        return cate;
    }

    public void setCate(int cate) {
        this.cate = cate;
    }

    public long getPay_time() {
        return pay_time;
    }

    public void setPay_time(long pay_time) {
        this.pay_time = pay_time;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Complain getComplain() {
        return complain;
    }

    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public int getOrd_status() {
        return ord_status;
    }

    public void setOrd_status(int ord_status) {
        this.ord_status = ord_status;
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

    public String getThird_id() {
        return third_id;
    }

    public void setThird_id(String third_id) {
        this.third_id = third_id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "OrdersDetailsData{" +
                "oid='" + oid + '\'' +
                ", create_time=" + create_time +
                ", ord_status=" + ord_status +
                ", username='" + username + '\'' +
                ", trait='" + trait + '\'' +
                ", userid=" + userid +
                ", third_id='" + third_id + '\'' +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                ", pics=" + Arrays.toString(pics) +
                ", cover='" + cover + '\'' +
                ", video_url='" + video_url + '\'' +
                ", price=" + price +
                ", jobid=" + jobid +
                ", job_time=" + job_time +
                ", job_pic=" + Arrays.toString(job_pic) +
                ", pay_money=" + pay_money +
                ", cate=" + cate +
                ", pay_time=" + pay_time +
                ", label=" + label +
                ", comment=" + comment +
                ", complain=" + complain +
                '}';
    }
}
