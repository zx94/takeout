package com.example.demo.controller;

import com.example.demo.entity.Order;
import com.example.demo.entity.Seller;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("order", service.getAllOrders());
        return "order/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("order", new Seller());
        return "order/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Order order, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.createOrder(order);
        }
        return "redirect:/order/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("order", service.getById(id));
        return "order/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute Order order, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.updateOrder(order.getId(),order);
        }
        return "redirect:/order/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        service.deleteOrder(id);
        return "redirect:/order/index";
    }

}
