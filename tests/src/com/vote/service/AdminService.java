package com.vote.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import com.vote.bean.AdminBean;
import com.vote.bean.ObjectBean;


public class AdminService {
	
	public AdminBean longin(String username,String password,String teamname) throws Exception {
		DBConnection dbcon = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AdminBean admin=new AdminBean();
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
			admin.setPassword(pwd);
			admin.setUsername(uname);
			admin.setTeam(tname);
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			dbcon.closeAll(con, ps, rs);
		}
		return admin;
	}
	
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
	
	public static String getOldPsw(String username){
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
	
	public static boolean updatePassword(String username,String newpsw) throws Exception{
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
	
	public static List ListUser(String teamname) {
		DBConnection dbcon = new DBConnection();
		Connection con = null;
		Statement stm = null;
		ResultSet rs = null;
		List userList=new LinkedList();
		try {
			String sql = "select id,username from wj_admins where team ='" + teamname + "' order by id desc";
			con=dbcon.getConnection();
			stm=con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()){
				AdminBean user=new AdminBean();
				int id =rs.getInt("id");
				String username=rs.getString("username");
				
				user.setId(id);
				user.setUsername(username);

				userList.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
				dbcon.closeAll(con,stm,rs);
		}
		return userList;
	}
	
	// 删除问卷
	public static boolean delUser(int uid) throws Exception {
		DBConnection dbcon = new DBConnection();
		boolean flag = false;
		Connection conn = dbcon.getConnection();
		conn.setAutoCommit(false);
		String sql1 = "delete from wj_admins where id="+uid;
		try {
			dbcon.update(sql1, conn);
			conn.commit();
			flag = true;
		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
				dbcon.closeAll(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}
