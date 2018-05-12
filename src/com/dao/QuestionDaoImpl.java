package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.entity.Question;
import com.entity.User;
import com.util.DBconn;

import javax.sql.RowSet;

public class QuestionDaoImpl implements QuestionDao{

    private List<Question> list_question=null;

    public boolean addQuestion(Question question) throws SQLException {
        Connection conn = DBconn.init();
        PreparedStatement pstmt=null;
        String sql = "insert into question(q_id,q_name,q_desc,q_author,q_IsRepeat,q_pwd,q_isOpean,q_hits,q_answer) values(?,?,?,?,?,?,?,?,?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, question.getQId());
            pstmt.setString(2, question.getQName());
            pstmt.setString(3, question.getQDesc());
            pstmt.setString(4, question.getQAuthor());
            pstmt.setBoolean(5, question.getQIsRepeat());
            pstmt.setString(6, question.getQPassword());
            pstmt.setBoolean(7, question.getQIsOpen());
            pstmt.setInt(8, question.getQAnswer());

            return pstmt.executeUpdate()==1?true:false;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }finally{
            pstmt.close();
            DBconn.closeConn();
        }
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
    public boolean updateQuestion(int questionId) throws SQLException {
        boolean flag = false;
        Connection conn = DBconn.init();
        PreparedStatement pstmt = null;
        // 没写完
        String sql = "update question.(q_id,q_name,q_desc,q_author,q_IsRepeat,q_pwd,q_isOpean,q_expireDate,q_hits,q_answer) values(?,?,?,?,?,?,?,?,?,?) where q_id=?" + questionId;
        Question question = new Question();
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, question.getQId());
            pstmt.setString(2, question.getQName());
            pstmt.setString(3, question.getQDesc());
            pstmt.setString(4, question.getQAuthor());
            pstmt.setBoolean(5, question.getQIsRepeat());
            pstmt.setString(6, question.getQPassword());
            pstmt.setBoolean(7, question.getQIsOpen());
            pstmt.setInt(8, question.getQAnswer());
            return pstmt.executeUpdate()==1?true:false;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }finally{
            pstmt.close();
            DBconn.closeConn();
        }
    }
    public boolean deleteQuestion(int questionId) {
        boolean flag = false;
        DBconn.init();
        String sql = "delete  from question where q_id="+questionId;
        int i =DBconn.addUpdDel(sql);
        if(i>0){
            flag = true;
        }
        DBconn.closeConn();
        return flag;
    }

//    public List<Question> listQuestions(String WhereClause){
//        boolean flag = false;
//    }
//    public List<Question> listAllQuestion(Long surveyId){
//        boolean flag = false;
//    }
//    public List<Question> listAllQuestion(int qId,String ascORdesc){
//        return this.listAllQuestion(qId, "asc");
//    }
//    public User searchQuestion(String username) {
//        boolean flag = false;
//        User user = new User();
//        return user;
//    }
}