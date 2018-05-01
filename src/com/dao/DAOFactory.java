package com.dao;

import com.entity.Question;

public class DAOFactory {
    private DAOFactory(){

    }

    public static UserDao getUserDAO(){
        return new UserDaoImpl();
    }

    public static QuestionDao getQuestionDAO(){
        return new QuestionDaoImpl();
    }
}
