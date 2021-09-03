package com.dodo.mblog.entity;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.Date;




public class Blog {

    @Resource
    private Integer id;         // blog id
    private String title;       // 標題
    private String author;      // 作者
    private Date createtime;    // 創建時間
    private Date updatetime;    // 最後更新時間
    private String summary;     // 文章概要
    private String content;     // 文章內容
    private String mkcontent;   // markdown源文件
    private String classname;    // 分類
    private Integer istop;      // 是否置頂

    public String getMkcontent() {
        return mkcontent;
    }

    public void setMkcontent(String mkcontent) {
        this.mkcontent = mkcontent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Integer getIstop() {
        return istop;
    }

    public void setIstop(Integer istop) {
        this.istop = istop;
    }
}
