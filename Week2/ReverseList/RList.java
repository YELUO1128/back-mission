package com.YELUO.Test.ReverseList;

import com.YELUO.LinkList.MyLink;

public class RList {
//    class Node{
//        Node next = null;
//        int data;
//
//        public Node(int data){
//            this.data = data;
//        }
//    }
    MyNode head = null;

    //插入数据
    public void addNode(int d){
        MyNode newNode = new MyNode(d);
        if(head == null){
            head = newNode;
            return;
        }
        MyNode temp = head;
        while(temp.next!=null){
            temp = temp.next;
        }
        temp.next = newNode;
    }

    //反转
    public MyNode RL(MyNode head){
        MyNode cur = head, pre = null;
        while(cur != null) {
            MyNode tmp = cur.next; // 暂存后继节点 cur.next
            cur.next = pre;          // 修改 next 引用指向
            pre = cur;               // pre 暂存 cur
            cur = tmp;               // cur 访问下一节点
        }
        return pre;
    }
    public void printList() {
        MyNode tmp = head;
        while (tmp != null) {
            System.out.print(tmp.data+" ");
            tmp = tmp.next;
        }
        System.out.println();
    }
}


