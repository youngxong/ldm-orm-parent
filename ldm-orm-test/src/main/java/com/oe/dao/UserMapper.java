package com.oe.dao;

import com.oe.pojo.User;

import java.util.List;

public interface UserMapper {
    //查询所有用户
    public List<User> findAll() throws Exception;


    //根据条件进行用户查询
    public User findByCondition(User user) throws Exception;


    public int insert(User user) throws Exception;

    public int delete(User user) throws Exception;

    public int update(User user) throws Exception;


}
