package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.entity.User;
import com.util.MD5Util;
import com.util.DBConnection;

import javax.sql.RowSet;

public class UserDaoImpl implements UserDao{

    public boolean register(String username,String password,String teamname) throws Exception {
        DBConnection db = new DBConnection();
        Connection con=null;
        PreparedStatement stmt=null;
        ResultSet rs = null;
        String sql = "";

        String md5Psw = MD5Util.MD5Encrypt(password);

        try {
            con = db.getConnection();
            sql = "insert into wj_admins(username,password,team) values(?,?,?)";
            System.out.println(sql);
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, md5Psw);
            stmt.setString(3, teamname);

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.closeAll(con, stmt,rs);
        }

        return true;
    }

    public User login(String username,String password,String teamname) throws Exception{
        DBConnection dbcon = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user=new User();
        String md5Psw = MD5Util.MD5Encrypt(password);
        String sql = "select * from wj_admins where username =? and password =? and team=?";//防止SQL注入
        try {
            dbcon=new DBConnection();
            con=dbcon.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, md5Psw);
            ps.setString(3, teamname);
            rs = ps.executeQuery();
            while(rs.next()){
                String uname=rs.getString("username");
                String pwd=rs.getString("password");
                String tname=rs.getString("team");
                user.setPassword(pwd);
                user.setUsername(uname);
                user.setTeam(tname);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            dbcon.closeAll(con, ps, rs);
        }
        return user;
    }


    public String getOldPsw(String username){
        DBConnection dbcon = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String psw ="";
        String	sql = "select * from wj_admins where username =?";
        try {
            dbcon=new DBConnection();
            con=dbcon.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            while(rs.next()){
                psw=rs.getString("password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbcon.closeAll(con, ps, rs);
        }
        return psw;
    }

    public boolean updatePassword(String username,String newpsw) throws Exception{
        DBConnection dbcon = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean flag = false;
        String md5Psw = MD5Util.MD5Encrypt(newpsw);
        String	sql = "update wj_admins set password = ? where username = ?";
        System.out.println(sql);
        try {
            dbcon=new DBConnection();
            con=dbcon.getConnection();
            ps=con.prepareStatement(sql);
            ps.setString(1, md5Psw);
            ps.setString(2, username);
            ps.executeUpdate();
            flag=true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dbcon.closeAll(con, ps, rs);
        }
        return flag;
    }
}