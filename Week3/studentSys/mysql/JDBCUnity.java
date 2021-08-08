package com.YELUO.studentSys.mysql;

import java.sql.*;

public class JDBCUnity {
    public static Connection getconnection(String type) throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        //建立连接
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/"+type+"useSSL=false&serverTimezone=UTC&characterEncoding=utf-8","root","001128");
    }
    public static void close(PreparedStatement ps,ResultSet rs,Connection con){
        try{
            ps.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            rs.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void close(ResultSet rs,Connection con){
        try{
            rs.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
        try{
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
    public static void close(Connection con){
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
