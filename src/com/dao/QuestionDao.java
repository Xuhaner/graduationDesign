package com.dao;

import java.util.List;

import com.entity.Question;

public interface QuestionDao {

    public boolean addQuestion(Question question);//注册

   // public List<Question> getQuestionAll();//返回用户信息集合

    public boolean deleteQuesiton(int questionId);//根据id删除用户

    public boolean deleteOption(int optionId);

    public boolean updateQuestion(Question question);//更新用户信息

    List<Question> listQuestions(String WhereClause);
    List<Question> listAllQuestion(Long surveyId);
    List<Question> listAllQuestion(int qId,String ascORdesc);

    public Question findQuestion(Long questionId);
}