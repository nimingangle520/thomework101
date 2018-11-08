package com.shushan.thomework101.HttpHelper.service.entity.register;

import java.util.Arrays;

public class LoginData {
    private int tid;
    private String tch_name;
    private String intro;
    private String trait;
    private int tch_label;
    private String token;
    private String third_id;
    private String third_token;
    private String online;
    private String [] grade;
    private String subject;
    private int sex;
    private String birthday;
    private String province;
    private String city;
    private String country;
    private float score;
    private int stu_num;
    private int follower_num;
    private float guide_price;
    private int guide_num;
    private int check_num;
    private String level;
    private int guide_time;
    private int edu_stage;
    private int edu_exp;
    private String college;
    private String edu_auth;
    private int cooperation;
    private int auth_edu;
    private int auth_id;
    private int auth_teach;
    private int auth_skill;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTch_name() {
        return tch_name;
    }

    public void setTch_name(String tch_name) {
        this.tch_name = tch_name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTrait() {
        return trait;
    }

    public void setTrait(String trait) {
        this.trait = trait;
    }

    public int getTch_label() {
        return tch_label;
    }

    public void setTch_label(int tch_label) {
        this.tch_label = tch_label;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getThird_id() {
        return third_id;
    }

    public void setThird_id(String third_id) {
        this.third_id = third_id;
    }

    public String getThird_token() {
        return third_token;
    }

    public void setThird_token(String third_token) {
        this.third_token = third_token;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String[] getGrade() {
        return grade;
    }

    public void setGrade(String[] grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getStu_num() {
        return stu_num;
    }

    public void setStu_num(int stu_num) {
        this.stu_num = stu_num;
    }

    public int getFollower_num() {
        return follower_num;
    }

    public void setFollower_num(int follower_num) {
        this.follower_num = follower_num;
    }

    public float getGuide_price() {
        return guide_price;
    }

    public void setGuide_price(float guide_price) {
        this.guide_price = guide_price;
    }

    public int getGuide_num() {
        return guide_num;
    }

    public void setGuide_num(int guide_num) {
        this.guide_num = guide_num;
    }

    public int getCheck_num() {
        return check_num;
    }

    public void setCheck_num(int check_num) {
        this.check_num = check_num;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getGuide_time() {
        return guide_time;
    }

    public void setGuide_time(int guide_time) {
        this.guide_time = guide_time;
    }

    public int getEdu_stage() {
        return edu_stage;
    }

    public void setEdu_stage(int edu_stage) {
        this.edu_stage = edu_stage;
    }

    public int getEdu_exp() {
        return edu_exp;
    }

    public void setEdu_exp(int edu_exp) {
        this.edu_exp = edu_exp;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getEdu_auth() {
        return edu_auth;
    }

    public void setEdu_auth(String edu_auth) {
        this.edu_auth = edu_auth;
    }

    public int getCooperation() {
        return cooperation;
    }

    public void setCooperation(int cooperation) {
        this.cooperation = cooperation;
    }

    public int getAuth_edu() {
        return auth_edu;
    }

    public void setAuth_edu(int auth_edu) {
        this.auth_edu = auth_edu;
    }

    public int getAuth_id() {
        return auth_id;
    }

    public void setAuth_id(int auth_id) {
        this.auth_id = auth_id;
    }

    public int getAuth_teach() {
        return auth_teach;
    }

    public void setAuth_teach(int auth_teach) {
        this.auth_teach = auth_teach;
    }

    public int getAuth_skill() {
        return auth_skill;
    }

    public void setAuth_skill(int auth_skill) {
        this.auth_skill = auth_skill;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "tid=" + tid +
                ", tch_name='" + tch_name + '\'' +
                ", intro='" + intro + '\'' +
                ", trait='" + trait + '\'' +
                ", tch_label=" + tch_label +
                ", token='" + token + '\'' +
                ", third_id='" + third_id + '\'' +
                ", third_token='" + third_token + '\'' +
                ", online='" + online + '\'' +
                ", grade=" + Arrays.toString(grade) +
                ", subject='" + subject + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", score=" + score +
                ", stu_num=" + stu_num +
                ", follower_num=" + follower_num +
                ", guide_price=" + guide_price +
                ", guide_num=" + guide_num +
                ", check_num=" + check_num +
                ", level='" + level + '\'' +
                ", guide_time=" + guide_time +
                ", edu_stage=" + edu_stage +
                ", edu_exp=" + edu_exp +
                ", college='" + college + '\'' +
                ", edu_auth='" + edu_auth + '\'' +
                ", cooperation=" + cooperation +
                ", auth_edu=" + auth_edu +
                ", auth_id=" + auth_id +
                ", auth_teach=" + auth_teach +
                ", auth_skill=" + auth_skill +
                '}';
    }
}
