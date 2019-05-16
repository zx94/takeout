package com.example.demo.controller;

import com.example.demo.entity.OrderItems;
import com.example.demo.entity.Seller;
import com.example.demo.service.OrderItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orderItems")
public class OrderItemsController {

    @Autowired
    private OrderItemsService service;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("order", service.getAllOrderItems());
        return "orderItems/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("orderItems", new Seller());
        return "orderItems/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute OrderItems orderItems, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.createOrderItems(orderItems);
        }
        return "redirect:/orderItems/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("orderItems", service.getById(id));
        return "orderItems/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute OrderItems orderItems, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.updateOrderItems(orderItems.getId(),orderItems);
        }
        return "redirect:/orderItems/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        service.deleteOrderItems(id);
        return "redirect:/orderItems/index";
    }

}
