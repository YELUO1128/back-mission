package com.YELUO.Test.MyAbnormal;

public class AddEror {
    public void AddNums(){
        int i =1;
        while(true){
            i++;
            if(i>165){
                throw new NumberRuntimeException();
            }else{
                System.out.println("还未超出限制 :"+i);
            }
        }
    }
}
