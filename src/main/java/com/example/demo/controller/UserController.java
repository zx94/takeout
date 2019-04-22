package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {
    @Autowired
    private UserService userservice;

    @RequestMapping("/listUser")
    @ResponseBody
    public List<User> listUser(){
        return userservice.listUser();
    }

    @RequestMapping("/listUserByname")
    @ResponseBody
    public List<User> listUserByname(String name){
        return userservice.findByName(name);
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(int id) {
        int result = userservice.delete(id);
        if (result >= 1) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    @RequestMapping(value = "/fogotPassword", method = RequestMethod.POST)
    public String fogotPassword(User user) {
        long result = userservice.forgotPassword(user.getId(),user.getPassword());
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping(value = "/changeAuthority", method = RequestMethod.POST)
    public String changeAuthority(User user) {
        long result = userservice.changeAuthority(user.getId(),user.getAuthority());
        if (result >= 1) {
            return "修改成功";
        } else {
            return "修改失败";
        }
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public User insert(User user) {
        return userservice.insertUser(user);
    }
}
