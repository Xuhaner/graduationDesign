package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;
import com.util.DBconn;

import javax.sql.RowSet;

public class UserDaoImpl implements UserDao{

    public boolean register(User user) throws SQLException {
//        boolean flag = false;
//        DBconn.init();
//        int i = DBconn.addUpdDel("insert into user(u_name,u_pwd,u_email,u_team) " +
//                "values('"+user.getU_name()+"','"+user.getU_pwd()+"','"+user.getU_email()+"','"+user.getU_email()+"')");
//        System.out.println("insert into user(u_name,u_pwd,u_email,u_team) " +
//                "values('"+user.getU_name()+"','"+user.getU_pwd()+"','"+user.getU_email()+"','"+user.getU_email()+"')");
//        if(i>0){
//            flag = true;
//        }
//        DBconn.closeConn();
//        return flag;

        Connection conn = DBconn.init();
        PreparedStatement pstmt=null;
        String sql = "insert into user(u_name,u_pwd,u_email,u_team) values(?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getU_name());
            pstmt.setString(2, user.getU_pwd());
            pstmt.setString(3, user.getU_email());
            pstmt.setString(4, user.getU_team());


            return pstmt.executeUpdate()==1?true:false;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }finally{
            pstmt.close();
            DBconn.closeConn();
        }
    }
    public boolean login(String u_name, String u_pwd) {
        boolean flag = false;
        try {
            DBconn.init();
            ResultSet rs = DBconn.selectSql("select * from user where u_name='"+u_name+"' and u_pwd='"+u_pwd+"'");
            while(rs.next()){
                if(rs.getString("u_name").equals(u_name) && rs.getString("u_pwd").equals(u_pwd)){
                    flag = true;
                }
            }
            DBconn.closeConn();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }
//    public List<User> getUserAll() {
//        List<User> list = new ArrayList<User>();
//        try {
//            DBconn.init();
//            ResultSet rs = DBconn.selectSql("select * from user");
//            while(rs.next()){
//                User user = new User(u_id, u_name, u_pwd, u_email, u_team, u_createTime);
//                user.setId(rs.getInt("u_id"));
//                user.setName(rs.getString("name"));
//                user.setPwd(rs.getString("pwd"));
//                user.setSex(rs.getString("sex"));
//                user.setEmail(rs.getString("email"));
//                list.add(user);
//            }
//            DBconn.closeConn();
//            return list;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    public boolean update(User user) throws SQLException {
        boolean flag = false;
        Connection conn = DBconn.init();
        PreparedStatement pstmt = null;
        String sql = "UPDATE admins set a_user=?,a_pass=? where a_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            return pstmt.executeUpdate()==1?true:false;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }finally{
            pstmt.close();
            DBconn.closeConn();
        }
    }
    public boolean delete(int id) {
        boolean flag = false;
        DBconn.init();
        String sql = "delete  from user where u_id="+id;
        int i =DBconn.addUpdDel(sql);
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;
    }
    public User searchUser(String username) {
        boolean flag = false;
        User user = new User();
        return user;
    }
}