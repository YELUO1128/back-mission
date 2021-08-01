package com.YELUO.Test.MyStack;

import java.util.EmptyStackException;

public class MyStack {
    private int top = -1;
    private Object[] objs;

    public MyStack(int capacity) throws Exception{
        if(capacity < 0)
            throw new Exception("Illegal capacity:"+capacity);
        objs = new Object[capacity];
    }
    public boolean Empty(){
        if(top == -1){
            return true;
        }
        return false;
    }
    //入栈
    public void push(Object obj) throws Exception{
        if(top == objs.length - 1)
            throw new Exception("Stack is full!");
        objs[++top] = obj;
    }
    //出栈 并删除
    public Object pop() throws Exception{
        if(top == -1)
            throw new Exception("Stack is empty!");
        return objs[top--];
    }

    //获取栈顶值
    public Object peek()throws Exception{
        if(Empty())
            new EmptyStackException();
        return objs[top];
    }
    //查找
    public void search(Object obj){
        if(Empty())
            System.out.println("此栈为空");
        for(int i = 0;i<=top;i++){
            if(objs[i]==obj){
                System.out.println("此元素在栈中");
            }
        }
    }
    //打印
    public void dispaly(){
        System.out.print("bottom -> top: | ");
        for(int i = 0 ; i <= top ; i++){
            System.out.print(objs[i]+" | ");
        }
        System.out.print("\n");
    }
    public void dispaly2(){
        System.out.print("bottom -> top: | ");
        for(int i = 0 ; i <= top ; i++){
            System.out.print(Integer.toBinaryString((int)(objs[i])) +" | ");
        }
        System.out.print("\n");
    }
    public void dispaly8(){
        System.out.print("bottom -> top: | ");
        for(int i = 0 ; i <= top ; i++){
            System.out.print(Integer.toOctalString((int)(objs[i])) +" | ");
        }
        System.out.print("\n");
    }
    public void dispaly16(){
        System.out.print("bottom -> top: | ");
        for(int i = 0 ; i <= top ; i++){
            System.out.print(Integer.toHexString((int)(objs[i])) +" | ");
        }
        System.out.print("\n");
    }

    public static void main(String[] args) throws Exception{
        MyStack s = new MyStack(5);
        s.push(1);  //增
        s.push(2);
        s.dispaly();
        System.out.println(s.pop());  //出栈
        s.dispaly();
        s.push(99);
        s.push(56);
        s.push(82);
        s.push(73);
        s.dispaly();
        //提取顶端
        Object y= s.peek();
        System.out.println(y);
        //查找
        s.search(99);
        //二进制
        s.dispaly2();
        //八进制
        s.dispaly8();
        //十六进制
        s.dispaly16();

    }
}