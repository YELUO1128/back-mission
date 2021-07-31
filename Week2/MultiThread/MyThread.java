package com.YELUO.MultiThreading.work;

public class MyThread extends Thread{
    public MyThread(String name){
        super(name);
    }
    @Override
    public void run() {
        for(int i = 1;i<=120;i++){
            System.out.println(getName()+":"+i);
        }
    }
}
