package com.dodo.mblog.entity;

import java.util.Date;

/**
 * @Auther: Administrator
 * @Date: 2018/5/28 10:27
 * @Description: 用户详细信息表
 */
public class UserDetails {

    private String email;
    private String username;
    private String imageurl;
    private Long phone;
    private Integer age;
    private Date regtime;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    @Override
    public String toString() {
        return "UserDetails: " + " userid : " + getEmail()+ " username: " + getUsername() + " imageurl: " +getImageurl()
                + " phone : " + getPhone() + "age: " + getAge() + " regtime："  + getRegtime();
    }
}
