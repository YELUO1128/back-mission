package com.YELUO.MultiThreading.work;

import java.util.concurrent.FutureTask;

public class Mytest {
    public static void main(String[] args) throws InterruptedException {
        //线程1
        MyThread t1 = new MyThread("线程·1");
        //线程2
        MyRunnable t0 = new MyRunnable();
        Thread t2 = new Thread(t0,"线程·2");
        //线程3
        MyCallable<String> t = new MyCallable<String>();
        FutureTask<String> t3 = new FutureTask<String>(t);
        t1.start();
        t2.start();
        new Thread(t3,"线程·3").start();

    }
}
