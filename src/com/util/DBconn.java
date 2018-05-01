package com.util;

import java.sql.*;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;




public class DBconn {
    static String url = "jdbc:mysql://localhost:3306/question";
    static String username = "root";
    static String password = "root";
    static Connection  conn = null;
    static ResultSet rs = null;
    static PreparedStatement ps =null;
    public static Connection init(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("init [SQL驱动程序初始化成功！]");
            return conn;
        } catch (Exception e) {
            System.out.println("init [SQL驱动程序初始化失败！]");
            e.printStackTrace();
            return conn;
        }
    }
    public static int addUpdDel(String sql){
        int i = 0;
        try {
            PreparedStatement ps =  conn.prepareStatement(sql);
            i =  ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("sql数据库增删改异常");
            e.printStackTrace();
        }

        return i;
    }
    public static ResultSet selectSql(String sql){
        try {
            ps =  conn.prepareStatement(sql);
            rs =  ps.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("sql数据库查询异常");
            e.printStackTrace();
        }
        return rs;
    }
    public void queryRowSet(String sql) {

        try {
            ps =  conn.prepareStatement(sql);
            rs =  ps.executeQuery(sql);
            boolean flag = false;
        } catch (SQLException e) {
            System.out.println("sql数据库查询异常");
            e.printStackTrace();
        }
    }
    public static void closeConn(){
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("sql数据库关闭异常");
            e.printStackTrace();
        }
    }
}