package com.example.demo.controller;

import com.example.demo.entity.AuthorityEnum;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userservice;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", userservice.getAllUsers());
        return "user/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("authorityNames", AuthorityEnum.values());
        return "user/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute User user, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
           userservice.createUser(user);
        }
        return "redirect:/user/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("user", userservice.getById(id));
        model.addAttribute("authorityNames", AuthorityEnum.values());
        return "user/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute User user, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            userservice.updateUser(user.getId(),user);
        }
        return "redirect:/user/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        userservice.deleteUser(id);
        return "redirect:/user/index";
    }
}
