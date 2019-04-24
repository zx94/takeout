package com.example.demo.service;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User insertUser(User user) {
        userMapper.insertUser(user);
        return user;
    }

    public List<User> listUser(){
        return userMapper.listUser();
    }

    public List<User> findByName(String name) {
        return userMapper.findUserByName(name);
    }

    public User findById(long id) {
        return userMapper.findUserById(id);
    }

    public long changeAuthority(long id,String authority){
        User user=findById(id);
        user.setAuthority(authority);
        return userMapper.update(user);
    }

    public long forgotPassword(long id,String password){
        User user=findById(id);
        user.setPassword(password);
        return userMapper.update(user);
    }

    public int delete(long id){
        return userMapper.delete(id);
    }
}
