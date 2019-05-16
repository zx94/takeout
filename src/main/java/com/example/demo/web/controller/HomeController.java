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
            if(user.getAuthorityName().equals("Admin"))
                return "redirect:/user/index";
            if(user.getAuthorityName().equals("Seller"))
                return "redirect:/seller/index";
            else
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

        if(data_user==null){
            model.addAttribute("hintMessage","用户不存在！");
            return "login";
        }

        if(null==user.getPassword()||user.getPassword().isEmpty()){
            model.addAttribute("hintMessage","密码不能为空！");
            return "login";
        }
        if (null != user.getUserName()||!user.getPassword().isEmpty()) {
            if(data_user.getPassword().equals(user.getPassword()))  {
                session.setAttribute(USER_SESSION_KEY, data_user);
                if(data_user.getAuthorityName().equals("Admin"))
                    return "redirect:/user/index";
                if(data_user.getAuthorityName().equals("Seller"))
                    return "redirect:/seller/index";
                else
                    return "redirect:/welcome";
            }
            else {
                model.addAttribute("hintMessage","账户或密码错误！请重新输入！");
                return "login";
            }
        } else {
            model.addAttribute("hintMessage","用户名不能为空！");
            return "login";
        }
    }

    @GetMapping(value = "/register")
    public String register(HttpSession session,Model model) {
        final User user = (User) session.getAttribute(USER_SESSION_KEY);
        //如果session存在，跳转到后台首页
        if (null != user) {
            model.addAttribute("user",user);
            if(user.getAuthorityName().equals("Admin"))
                return "redirect:/user/index";
            if(user.getAuthorityName().equals("Seller"))
                return "redirect:/seller/index";
            else
                return "redirect:/welcome";
        }

        model.addAttribute("user",new User());
        return "login";
    }

    /**
     * 注册 POST请求
     *
     */
    @PostMapping(value = "/register")
    public String getRegister(@ModelAttribute User user, HttpSession session, Model model) {
        //注册验证
        if (null == user.getUserName()||user.getUserName().isEmpty()) {
            model.addAttribute("hintMessage","用户名不能为空！");
            return "login";
        }
        if (null==user.getPassword()||user.getPassword().isEmpty()) {
            model.addAttribute("hintMessage","密码不能为空！");
            return "login";
        }
        User data_user = userService.getByUserName(user.getUserName());
        if(null!=data_user){
            model.addAttribute("hintMessage","用户名已存在！");
            return "login";
        }
        if(user.getPassword().length()<6||user.getPassword().length()>22){
            model.addAttribute("hintMessage","密码应在6-22位之间！");
            return "login";
        }

        userService.createUser(user);

        data_user= userService.getByUserName(user.getUserName());

        session.setAttribute(USER_SESSION_KEY, data_user);

        if(data_user.getAuthorityName().equals("Admin"))
            return "redirect:/user/index";
        if(data_user.getAuthorityName().equals("Seller"))
            return "redirect:/seller/index";
        else
            return "redirect:/welcome";
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
