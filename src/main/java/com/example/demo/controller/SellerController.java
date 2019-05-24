package com.example.demo.controller;

import com.example.demo.entity.Seller;
import com.example.demo.entity.User;
import com.example.demo.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.example.demo.helper.SysConst.USER_SESSION_KEY;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService service;

    @GetMapping("/index")
    public String index(HttpSession session, Model model) {
        final User user = (User) session.getAttribute(USER_SESSION_KEY);
        if(user.getAuthorityName().equals("Seller"))
            return "index/seller_index";
        model.addAttribute("seller", service.getAllSellers());
        return "seller/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("seller", new Seller());
        return "seller/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Seller seller, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.createSeller(seller);
        }
        return "redirect:/seller/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("seller", service.getById(id));
        return "seller/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute Seller seller, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.updateSeller(seller.getId(),seller);
        }
        return "redirect:/seller/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        service.deleteSeller(id);
        return "redirect:/seller/index";
    }
}
