package com.YELUO.studentSys.serve;

import com.YELUO.studentSys.Menu.studentM;
import  java.util.*;

@SuppressWarnings("all")
public class mainServe {
    public static void main(String[] args) throws Exception {
        int choice;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("+++++++++++++++++++0.退出系统");
            System.out.println("+++++++++++++++++++1.使用系统");
            choice = scanner.nextInt();
            if (choice == 0) break;
            else if (choice == 1) {
                start();
                break;
            }
        }
    }

    public static void start() throws InterruptedException {

        studentM sm = new studentM();
        sm.menu();

    }
}
