package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据操作层-DAO
 */
@Component
public interface UserMapper {
    void create(User user);
    void delete(Long id);
    void update(Long id, User u);

    void active(Long id);

    List<User> findAll();
    User findById(Long id);
    User findByUserName(String userName);

}

