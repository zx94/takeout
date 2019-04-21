package com.example.demo.mapper;
import com.example.demo.entity.User;

import java.util.List;

/**
 * 数据操作层-DAO
 */
public interface UserMapper {

    List<User> findUserByName(String name);

    List<User> ListUser();

    int insertUser(User user);

    int delete(int id);

    int Update(User user);
}

