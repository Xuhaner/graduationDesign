package com.dao;

import com.entity.Option;
import com.util.DBconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OptionDaoImpl implements OptionDao {

    public boolean addOption(Option option) throws SQLException {
        Connection conn = DBconn.init();
        PreparedStatement pstmt=null;
        String sql = "insert into option(o_id,q_id,o_type,o_head,o_body,o_result,o_img,o_order) values(?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, option.getOId());
            pstmt.setInt(2, option.getQId());
            pstmt.setInt(3, option.getOType());
            pstmt.setString(4, option.getOHead());
            pstmt.setString(5, option.getOBody());
            pstmt.setString(6, option.getOResult());
            pstmt.setString(7, option.getOImg());
            pstmt.setString(8, option.getOOrder());

            return pstmt.executeUpdate()==1?true:false;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally{
            pstmt.close();
            DBconn.closeConn();
        }
    }

    public boolean deleteOption(int qID) throws SQLException {
        try{
            DBconn.init();
            return -1 != DBconn.addUpdDel("delete from question where q_id="
                    + qID);
        }finally {
            DBconn.closeConn();
        }
    }

    public boolean updateOption(int Qid) throws SQLException {
        boolean flag = false;
        return flag;
    }
}
