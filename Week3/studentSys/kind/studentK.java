package com.YELUO.studentSys.kind;

public class studentK {
    private String id;
    private String sname;
    private int mark;

    public studentK(String id,String name,int mark){
        this.id = id;
        this.sname = name;
        this.mark = mark;
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getSname(){
        return sname;
    }
    public void setSname(String name){
        sname = name;
    }
    public int getMark(){
        return mark;
    }
    public void setMark(int m){
        mark = m;
    }
}
