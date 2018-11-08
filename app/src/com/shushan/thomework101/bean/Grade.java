package com.shushan.thomework101.bean;

public class Grade {
    private String grade;
    boolean isSelected;

    public Grade(String grade, boolean isSelected) {
        this.grade = grade;
        this.isSelected = isSelected;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "grade='" + grade + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
