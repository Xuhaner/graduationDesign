package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.Question;

public interface QuestionDao {

    public boolean addQuestion(Question question) throws SQLException;//注册

    public boolean deleteQuestion(int questionId) throws SQLException;//根据id删除用户

    public boolean updateQuestion(int question) throws SQLException;//更新用户信息

//    public boolean deleteOption(int optionId);

//    List<Question> listQuestions(String WhereClause);
//    List<Question> listAllQuestion(Long surveyId);
//    List<Question> listAllQuestion(int qId,String ascORdesc);

//    public Question findQuestion(int questionId);
}