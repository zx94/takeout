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

    @RequestMapping("/welcome")
    ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome");
        modelAndView.addObject("message","Welcome to Spring Boot & Freemarker");
        return modelAndView;
    }


}
