package com.YELUO.studentSys.Menu;

import com.YELUO.studentSys.mysql.JDBCUnity;
import com.YELUO.studentSys.kind.studentK;
import java.sql.*;
import java.util.*;
public class studentM {
    int ID;
    public studentM(){
    }
    List<studentK> list = new ArrayList<>();


    public void menu(){
        int choice;
        Scanner scanner = new Scanner(System.in);
        while(true){
            read();
            System.out.println("\t\t\t----------------1.信息添加");
            System.out.println("\t\t\t----------------2.信息查看");
            System.out.println("\t\t\t----------------3.信息修改");
            System.out.println("\t\t\t----------------4.信息删除");
            System.out.println("\t\t\t----------------5.信息查找");
            System.out.println("\t\t\t----------------6.信息查找不及格");
            System.out.println("\t\t\t----------------0.退出");
            System.out.println("\t\t\t---输入你要进行的操作");
            choice = scanner.nextInt();
            if(choice==0) break;
            switch (choice){
                case 1:
                    add();        //添加
                    break;
                case 2:
                    show();
                    break;         //查看
                case 3:
                    change();
                    break;           //修改
                case 4:
                    delete();
                    break;         //删除
                case 5:
                    find();
                    break;
                case 6:
                    sort();
                    break;
            }
        }
    }
    public void find(){
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        System.out.println("-----------请输入查询的学号");
        String id = scanner.nextLine();

        try{
            con = JDBCUnity.getconnection("studentsys");
            ps = con.prepareStatement("select * from student where id=?");
            ps.setString(1,id);
            rs = ps.executeQuery();
            System.out.println("*****************************************");
            while(rs.next()){
                System.out.println("\t\t|学号："+rs.getString(1)+"|"+"姓名:"+rs.getString(2)+"|"+"成绩:"+rs.getInt(3));
            }
            System.out.println("++++++++++++++++++++++++++++++");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void change(){
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean f = false;
        System.out.println("------ 请输入学号");
        String id = scanner.nextLine();
        for(studentK t:list){
            if(t.getId().equals(id)){
                f = true;
                break;
            }
        }
        if(f == false){
            System.out.println("查无此人");
            return;
        }
        try{
            con = JDBCUnity.getconnection("studentsys");
            System.out.println("------姓名改为：");
            String name = scanner.nextLine();
            int m = scanner.nextInt();
            ps = con.prepareStatement("update student set sname=? mark = ? where id=? ");
            ps.setString (1,name);
            ps.setInt(2,m);
            ps.setString(3,id);
            ps.execute();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void sort(){
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        for(studentK k:list){
            if(k.getMark()<180){
                System.out.println("不及格的："+k.getSname()+"分数："+ k.getMark());
            }
        }
    }
    public void show (){
        System.out.println("++++++++++++++++++++++++++++++");
        for(studentK t:list){
            System.out.println("学号："+t.getId()+"姓名："+t.getSname()+"成绩："+t.getMark());
        }
        System.out.println("***********************");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void add(){
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int choice ;
        while(true){
            try{
                con = JDBCUnity.getconnection("studentsys");
                ps = con.prepareStatement("insert  into student(id,sname,mark) value(?,?,?)");
                System.out.println("请输入id");
                String id2 = scanner.nextLine();
                System.out.println("请输入姓名");
                String sn = scanner.nextLine();
                System.out.println("请输入分数");
                int sm = scanner.nextInt();
                ps.setObject(1,id2);
                ps.setObject(2,sn);
                ps.setObject(3,sm);
                ps.execute();
            }catch(Exception e){
                e.printStackTrace();
            }
            System.out.println("添加学生 按1，退出按0");
            choice = scanner.nextInt();
            if(choice ==0)break;
        }
    }

    public void delete(){
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = JDBCUnity.getconnection("studentsys");
            ps = con.prepareStatement("delete from student where id=?");
            System.out.println("输入id");
            String id = scanner.nextLine();
            if (id.equals("")){
                System.out.println("\t\t\t\t学号不能为空！");
                return;
            }
            ps.setObject(1,id);
            ps.execute();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void read(){
        list.clear();
        Scanner scanner = new Scanner(System.in);
        Connection con = null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        try{
            con = JDBCUnity.getconnection("studentsys");
            ps = con.prepareStatement("select id,sname,mark from student where id>=?");
            ps.setObject(1,0);
            rs = ps.executeQuery();

            while(rs.next()){
               studentK stu =new studentK(rs.getString(1),rs.getString(2),rs.getInt(3));
               list.add(stu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
