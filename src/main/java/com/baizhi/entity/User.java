package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Created by 白鹿 on 2018/8/6.
 */
public class User {
    private int id;
    private String name;
    private String dharma;
    private String photo;
    private String password;
    private String salt;
    private int sex;
    private String location;
    private String sign;
    private String status;
    private int phoneNum;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date registDate;

    public User() {
    }

    public User(int id, String name, String dharma, String photo, String password, String salt, int sex, String location, String sign, String status, int phoneNum, Date registDate) {
        this.id = id;
        this.name = name;
        this.dharma = dharma;
        this.photo = photo;
        this.password = password;
        this.salt = salt;
        this.sex = sex;
        this.location = location;
        this.sign = sign;
        this.status = status;
        this.phoneNum = phoneNum;
        this.registDate = registDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDharma() {
        return dharma;
    }

    public void setDharma(String dharma) {
        this.dharma = dharma;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dharma='" + dharma + '\'' +
                ", photo='" + photo + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", sex=" + sex +
                ", location='" + location + '\'' +
                ", sign='" + sign + '\'' +
                ", status='" + status + '\'' +
                ", phoneNum=" + phoneNum +
                ", registDate=" + registDate +
                '}';
    }
}
