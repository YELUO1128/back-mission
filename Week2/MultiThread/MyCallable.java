package com.YELUO.MultiThreading.work;

import java.util.concurrent.Callable;

public class MyCallable <String> implements Callable<String> {
    private int i = 1;
    @Override
    public String call() throws Exception {
        while(i<=120){
            System.out.println(Thread.currentThread().getName()+":"+i);
            i++;
        }
        return null;
    }
}
