package com.example.demo.controller;

import com.example.demo.entity.Member;
import com.example.demo.entity.Seller;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService service;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("member", service.getAllMembers());
        return "member/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("member", new Seller());
        return "member/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Member member, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.createMember(member);
        }
        return "redirect:/member/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("seller", service.getById(id));
        return "member/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute Member member, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.updateMember(member.getId(),member);
        }
        return "redirect:/member/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        service.deleteMember(id);
        return "redirect:/member/index";
    }

}
