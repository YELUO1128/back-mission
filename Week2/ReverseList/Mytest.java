package com.YELUO.Test.ReverseList;

public class Mytest {
    public static void main(String[] args) {
        RList r1 = new RList();

        r1.addNode(1);
        r1.addNode(2);
        r1.addNode(3);
        r1.addNode(4);
        r1.addNode(5);
        r1.addNode(6);
        //反转前
        r1.printList();
        r1.RL(r1.head);
        r1.printList();

    }
}
