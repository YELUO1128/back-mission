package com.dodo.mblog.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Auther: Administrator
 * @Date: 2018/5/27 09:53
 * @Description:
 */


public class User {

    private Integer userid;
    @NotNull
    @NotEmpty
    private String email;
    @Length(min = 6)
    @NotNull
    @NotEmpty
    private String pwd;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
