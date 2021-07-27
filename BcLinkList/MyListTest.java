package com.YELUO.BcLinkList;

public class MyListTest {
    public static void main(String[] args) {
        MyLinkList list = new MyLinkList();
        list.addHead(5);
        for(int i = 0;i < 15;i++){
            list.addTail(i);
        }
        System.out.println("原链表");
        list.printOederNode();
        System.out.println();
        list.deleteAppoint(8);
        System.out.println("删除8号之后");
        System.out.println();
        list.printOederNode();
        System.out.println("添加8号和16号之后");
        System.out.println();
        list.addAppoint(8,9);
        list.addAppoint(16,16);
        list.printOederNode();
    }
}
