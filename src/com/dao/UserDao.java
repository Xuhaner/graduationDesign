package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.entity.User;

public interface UserDao {
    public boolean login(String name,String pwd);//登录
    public boolean register(User user) throws SQLException;//注册
//    public List<User> getUserAll();//返回用户信息集合
    public boolean delete(int id) ;//根据id删除用户
    public boolean update(User user) throws SQLException;//更新用户信息
    public User searchUser(String username);

    //public boolean addTeam(int num);//num - the number of team

}
