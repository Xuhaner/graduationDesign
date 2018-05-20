package com.dao;

import java.awt.desktop.QuitStrategy;
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

    public List<Question> listAllQuestion(String order) {
        //wrong wrong wrong wrong wrong wrong wrong wrong wrong wrong wrong wrong
        DBconn.init();
        ResultSet rs= DBconn.selectSql("select * from question order by "+order);
        List<Question> list=new ArrayList<Question>();
        Question question;
        try {
            while(rs.next()){
                question =new Question();
                question.setQId(rs.getInt("q_id"));
                question.setQName(rs.getString("s_name"));
                question.setQDesc(rs.getString("s_desc"));
                question.setQAuthor(rs.getString("s_author"));

                //question.setCreateDate(rs.getDate("s_createdate"));
                //question.setSIpLimitType(rs.getString("s_iplimittype"));
                //question.setIpRange(rs.getString("s_ipRange"));

                //question.setSPassword(rs.getString("s_password"));
                //question.setIsOpen(rs.getBoolean("s_isopen"));
                //question.setSExpireDate(rs.getDate("s_expiredate"));
               // question.setSIsAudited(rs.getBoolean("s_isaudited"));
                //question.setSHits(rs.getLong("s_hits"));
                //question.setSUsehits(rs.getLong("s_usehits"));

                list.add(question);
            }

            return list;

        } catch (SQLException e) {

            e.printStackTrace();
            return null;
        }

    }
    public boolean updateQuestion(Question questionId) throws SQLException {
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

    public Question findQuestion(int questionID){
        Question question = new Question();
        return question;
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