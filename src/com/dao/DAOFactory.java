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

    public static ObjectBeanDao getObjectBeanDAO(){
        return new ObjectBeanDaoImpl();
    }

    public static RepalyDao getRepalyDAO(){
        return new ReplayDaoImpl();
    }

    public static SelectDao getSelectDAO(){
        return new SelectDaoImpl();
    }


}
