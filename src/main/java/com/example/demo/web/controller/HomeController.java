package com.example.demo.web.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.example.demo.helper.SysConst.USER_SESSION_KEY;

@Controller
public class HomeController {

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    //后台主页
    @RequestMapping("/welcome")
    ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        return modelAndView;
    }

    //region 登录相关

    //登录请求
    @GetMapping(value = "/login")
    public String login(HttpSession session,Model model) {
        final User user = (User) session.getAttribute(USER_SESSION_KEY);
        //如果session存在，跳转到后台首页
        if (null != user) {
            model.addAttribute("user",user);
            return "redirect:/welcome";
        }

        model.addAttribute("user",new User());
        return "login";
    }

    /**
     * 登录 POST请求
     *
     */
    @PostMapping(value = "/login")
    public String getLogin(@ModelAttribute User user, HttpSession session, Model model) {
        //登陆验证
        User data_user = userService.getByUserName(user.getUserName());

        String a=user.getUserName();
        String b=data_user.getUserName();

        if(null==user.getPassword()||user.getPassword().isEmpty()){
            model.addAttribute("hintMessage","密码不能为空！");
            return "login";
        }
        if (null != user.getUserName()||!user.getPassword().isEmpty()) {
            if(data_user.getPassword().equals(user.getPassword()))  {
                session.setAttribute(USER_SESSION_KEY, data_user);
                return "redirect:/welcome";
            }
            else {
                model.addAttribute("hintMessage","密码错误！请重新输入！");
                return "login";
            }
        } else {
            model.addAttribute("hintMessage","用户名不能为空！");
            return "login";
        }
    }

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
        return "redirect:/login";
    }

    //endregion


}
