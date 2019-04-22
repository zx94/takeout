package com.example.demo.mapper;
import com.example.demo.entity.User;

import java.util.List;

/**
 * 数据操作层-DAO
 */
public interface UserMapper {

    List<User> findUserByName(String name);

    /**
     * 根据id获取用户信息
     */
    User findUserById(long id);

    List<User> listUser();

    int insertUser(User user);

    int delete(long id);

    long update(User user);
}

