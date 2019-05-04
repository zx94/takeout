package com.example.demo.web.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.example.demo.helper.SysConst.USER_SESSION_KEY;

//后台管理员控制器
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    //后台主页
    @GetMapping(value = {"", "/welcome"})
    public String index(Model model) {
        model.addAttribute("Hello world");
        return "/welcome";
    }

    //region 登录相关

    //登录请求
    @GetMapping(value = "/login")
    public String login(HttpSession session) {
        final User user = (User) session.getAttribute(USER_SESSION_KEY);
        //如果session存在，跳转到后台首页
        if (null != user) {
            return "redirect:/admin";
        }
        return "admin/admin_login";
    }

    /**
     * 登录 POST请求
     *
     * @param loginName 登录名
     * @param loginPwd  登录密码
     * @param session   用户Session
     * @return JSON
     */
//    @PostMapping(value = "/getLogin")
//    @ResponseBody
//    public ResultInfo getLogin(@ModelAttribute("loginName") String loginName, @ModelAttribute("loginPwd") String loginPwd, HttpSession session) {
//        //登陆验证
////        User user = Validator.isEmail(loginName)
//////                ? userService.loginByEmail(loginName, loginPwd)
//////                : userService.loginByUserName(loginName, loginPwd);
//////        if (null != user) {
//////            return new ResultInfo().Code(ResultCodeEnum.SUCCESS.getCode()).Msg("成功").Data(user);
//////        } else {
//////            return new ResultInfo().Code(ResultCodeEnum.FAIL.getCode()).Msg("失败");
//////        }
//        return new ResultInfo().Code(ResultCodeEnum.SUCCESS.getCode()).Msg("66666666");
//    }

    /**
     * 注销 GET请求
     *
     * @param session 用户Session
     * @return /admin/login
     */
    @GetMapping(value = "/logOut")
    public String logOut(HttpSession session) {
        final User user = (User) session.getAttribute(USER_SESSION_KEY);
        //销毁session
        session.removeAttribute(USER_SESSION_KEY);
        return "redirect:/admin/login";
    }

    //endregion
}
