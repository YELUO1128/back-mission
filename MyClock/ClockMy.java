package com.YELUO.Test;

import java.awt.*;
import java.util.Scanner;

public class ClockMy {
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("输入你的选择1：自己设置，2：重现在开始");
        int choice = scanner.nextInt();
        switch(choice){
            case 1:
                System.out.println("输入年份");
                int y = scanner.nextInt();
                System.out.println("输入月份");
                int M = scanner.nextInt();
                System.out.println("输入日期");
                int d = scanner.nextInt();
                System.out.println("输入小时");
                int h = scanner.nextInt();
                System.out.println("输入分钟");
                int m = scanner.nextInt();
                System.out.println("输入秒数");
                int s = scanner.nextInt();
                Clock Myclock = new Clock(y,M,d,h,m,s);
                while(true){
                    Myclock.tick();
                    System.out.println(Myclock);
                    scanner.close();
                    Robot robot= null;
                    try {
                        robot = new Robot();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    robot.delay(1000);
                }
            case 2:
                Clock Myclock2 = new Clock();
                while(true){
                    Myclock2.tick();
                    System.out.println(Myclock2);
                    scanner.close();
                    Robot robot= null;
                    try {
                        robot = new Robot();
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    robot.delay(1000);
                }
        }

    }

}
