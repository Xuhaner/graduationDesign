package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.User;

public interface UserDao {
    public User login(String name,String pwd,String team)throws Exception;//登录
    public boolean register(String username,String password,String teamname) throws Exception;//注册
    public String getOldPsw(String username);
    public boolean updatePassword(String username,String newpsw) throws Exception;
}
