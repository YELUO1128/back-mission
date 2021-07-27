package com.YELUO.BcLinkList;


public class MyLinkList {
    MyNode head;
    int leng = 0;

    //构造
    public MyLinkList(){
        this.head = null;
    }

    //在头部添加数据
    public void addHead(int data){
        MyNode newNode = new MyNode(data);
        newNode.next = newNode;
        newNode.pre = newNode;
        if (head== null){
            head = newNode;
        }
        else{
            MyNode curNode = head;
            while(curNode.next != head){
                curNode = curNode.next;
            }
            newNode.setNext(head);         //设置循环
            curNode.setNext(newNode);
            newNode.setPre(curNode);
            head.setPre(newNode);
            head = newNode;
        }
        leng++;
    }
        //在头部删除节点
    public void deleteHae(){
        if(head ==null)
            System.out.println("此处为空");
        MyNode curNode = head;
        while(curNode.next!= head){
            curNode = curNode.next;
        }
        MyNode temp = head.next;
        curNode.next = temp;
        temp.pre = curNode;
        head = temp;
        leng--;
    }
    //在尾部添加节点
    public void addTail(int data){
        MyNode newNode = new MyNode(data);
        newNode.next = newNode;
        newNode.pre = newNode;
        if(head==null)
            head = newNode;
        else{
            MyNode curNode = head;
            while(curNode.next!=head){
                curNode = curNode.next;
            }
            curNode.next = newNode;
            newNode.pre = curNode;
            newNode.next = head;
            head.pre = newNode;
        }
        leng++;
    }
    //在指定位置添加节点
    public void addAppoint(int data,int index){
        MyNode newNode = new MyNode(data);
        newNode.next = newNode;
        newNode.pre = newNode;
        if(head == null)
            head = newNode;
        if(index> leng+1||index<1){
            System.out.println("节点插入位置错误，可插入位置为：1到"+(leng+1));
        }else{
         MyNode preNode = head;
         int cout = 1;
         while(cout < index-1){
             preNode = preNode.next;
             cout++;
         }
         MyNode curNode = preNode.next;
         curNode.pre = newNode;
         preNode.next = newNode;
         newNode.pre = preNode;
         newNode.next = curNode;
        }
        leng++;
    }
    //指定位置 删除节点
    public void deleteAppoint(int index) {

        if (index > leng || index < 1) {
            System.out.println("节点删除位置错误，可删除位置为：1到" + (leng));
        }
        else if(index == 1){
            MyNode curNode = head;
            while(curNode.next!= head){
                curNode= curNode.next;
            }
            MyNode temp = head.next;
            curNode.next=temp;
            temp.pre = curNode;
            head = temp;
            leng--;
        }else{
            MyNode preNode = head;
            int count = 1;
            while(count != index){
                preNode = preNode.next;
                count++;
            }
            MyNode laterNode = preNode.next;
            MyNode curNode = preNode.pre;
            curNode.next = laterNode;
            laterNode.pre = curNode;
            leng--;
        }
    }
    //查
    public void getIndexData (int index){
        if(head==null){
            System.out.println("空");
        }
        if(index > leng || index < 1) {
            System.out.println("结点位置不存在，可获取的位置为1到"+leng);
        }
        else {
            MyNode curNode = head;
            int count = 1;
            while (count != index) {
                curNode = curNode.next;
                count++;
            }
            System.out.println(curNode.data);

        }
    }
    //修改节点数据
    public void updateIndexData(int index,int data){
        if(head == null){
            System.out.println("空表");
        }
        if(index > leng || index < 1) {
            System.out.println("结点位置不存在，可更新的位置为1到"+leng);
        }else {
            int count = 1;
            MyNode temp = head;
            while (count != index){
                temp = temp.next;
                count++;
            }
            temp.data = data;
        }
    }
    //遍历链表
    public void printOederNode(){
        if(head ==null)
            System.out.println("空");
        MyNode curNode = head;
        while(curNode !=null){
            System.out.print(curNode.data+" ");
            curNode = curNode.next;
            if(curNode==head)
                break;
        }
    }
}
