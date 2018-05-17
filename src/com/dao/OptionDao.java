package com.dao;

import com.entity.Option;

import java.sql.SQLException;

public interface OptionDao {
    public boolean addOption(Option option) throws SQLException;//注册

    public boolean deleteOption(int optionId) throws SQLException;//根据id删除用户

    public boolean updateOption(int option) throws SQLException;//更新用户信息
}
