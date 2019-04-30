package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userservice;

    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;


    @GetMapping("/listUser")
    public ModelAndView listUser(){
        ModelAndView model = new ModelAndView("user/list");
        model.addObject("userList",userservice.listUser());
        return model;
    }

    @GetMapping("/insertPage")
    public ModelAndView insertPage(){
        ModelAndView modelAndView = new ModelAndView("user/insert");
        User user = new User();
        user.setId(snowflakeIdWorker.nextId());
        modelAndView.addObject("user", user);
        modelAndView.addObject("hintMessage", "初始化成功！");
        return modelAndView;
    }

    @PostMapping(value = "/insert")
    public ModelAndView insert(@Valid User user , BindingResult result) {
        //@Valid注解启动后台校验,
        ModelAndView modelAndView = new ModelAndView();
        if(result.hasErrors()){
            modelAndView.addObject("hintMessage", "出错啦！");
        }else{
            String userName = user.getUserName();
            User dataUser = userservice.findByName(userName);
            if(dataUser != null){
                modelAndView.addObject("hintMessage", "数据库已有该条记录！");
            }else{
                modelAndView.addObject("hintMessage", "提交成功！");
                userservice.insertUser(user);
            }
        }
        modelAndView.setViewName("redirect:user/list");

        return modelAndView;
    }

    @RequestMapping("/listUserById")
    @ResponseBody
    public User listUserById(long id){
        return userservice.findById(id);
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
}
