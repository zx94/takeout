package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

//    @GetMapping("/")
//    public String index(@SessionAttribute(WebSecurityConfig.SESSION_KEY) String account, Model model) {
//        model.addAttribute("name", account);
//        return "index";
//    }

//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @PostMapping("/loginPost")
//    public @ResponseBody
//    Map<String, Object> loginPost(String account, String password, HttpSession session) {
//        Map<String, Object> map = new HashMap<>();
//        if (!"123456".equals(password)) {
//            map.put("success", false);
//            map.put("message", "密码错误");
//            return map;
//        }
//
//        // 设置session
//        session.setAttribute(WebSecurityConfig.SESSION_KEY, account);
//
//        map.put("success", true);
//        map.put("message", "登录成功");
//        return map;
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        // 移除session
//        session.removeAttribute(WebSecurityConfig.SESSION_KEY);
//        return "redirect:/login";
//    }

    @RequestMapping("/welcome")
    ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        modelAndView.addObject("message","Welcome to Spring Boot & Freemarker");
        return modelAndView;
    }


}
