package com.company.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Ly
 * @version 1.0
 * @date 2021/8/13 12:38
 */
public class Student implements Serializable {
    private String stuUname;
    private String stuPassword;
    private String stuGender;
    private Date birthday;

    @Override
    public String toString() {
        return "Student{" +
                "stuUname='" + stuUname + '\'' +
                ", stuPassword='" + stuPassword + '\'' +
                ", stuGender='" + stuGender + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public String getStuUname() {
        return stuUname;
    }

    public void setStuUname(String stuUname) {
        this.stuUname = stuUname;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    public String getStuGender() {
        return stuGender;
    }

    public void setStuGender(String stuGender) {
        this.stuGender = stuGender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Student() {
    }

    public Student(String stuUname, String stuPassword, String stuGender, Date birthday) {
        this.stuUname = stuUname;
        this.stuPassword = stuPassword;
        this.stuGender = stuGender;
        this.birthday = birthday;
    }
}
