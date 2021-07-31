package com.YELUO.MultiThreading.work;

public class MyRunnable implements Runnable{
    @Override
    public void run() {
        for (int i = 1; i<=120;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}
