package com.dodo.mblog.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Administrator
 * @Date: 2018/6/8 09:24
 * @Description:
 */
public enum ErrorMessage {

    ACCOUNR_PWD_ERROR(10001,"账号密码错误"),
    RESET_PWD_SUCCUSS(10002,"密码修改成功，请重新登录"),
    RESET_PWD_ERROR(10003,"修改密码失败：原密码错误！");

    private int status;
    private String msg;


    ErrorMessage(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public static Map<Integer,String> getErrorMap(ErrorMessage message){
        Map<Integer,String> map = new HashMap();
        map.put(message.getStatus(),message.getMsg());
        return map;
    }

    public static void main(String[] args) {
        System.out.println(getErrorMap(ErrorMessage.ACCOUNR_PWD_ERROR).get(10001));
    }
}
