package com.YELUO.BcLinkList;

public class MyNode {
    int data;   //数据域
    MyNode next;  //后引用
    MyNode pre;   //前引用

    //构造
    public MyNode(int data){
        this.data = data;
        this.next = null;
        this.pre = null;
    }
    //数据

    //下一个节点

    //
    public void setNext(MyNode next) {
        this.next = next;
    }
    //上一个节点

    public void setPre(MyNode pre){
        this.pre = pre;
    }

}
