package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.Seller;
import com.example.demo.entity.User;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import java.util.List;

import static com.example.demo.helper.SysConst.USER_SESSION_KEY;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @Autowired
    private ProductsService productsService;

    @GetMapping("/index")
    public String index(Model model) {
        List<Order> orderList= service.getAllOrders();
        model.addAttribute("order", service.getAllOrders());
        return "order/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("order", new OrderDto());
        model.addAttribute("products", productsService.getAllProducts());
        return "order/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute OrderDto orderDto, BindingResult result, Model model, HttpSession session) {
        //@Valid注解启动后台校验,
        final User user = (User) session.getAttribute(USER_SESSION_KEY);
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            orderDto.setMemberName(user.getUserName());
            service.createOrder(orderDto);
        }
        return "redirect:/order/index";
    }

    @GetMapping("/pay/{id}")
    public String pay(@PathVariable Long id) {
        service.payOrder(id);
        return "redirect:/order/index";
    }

    @GetMapping("/acc/{id}")
    public String acc(@PathVariable Long id) {
        service.accOrder(id);
        return "redirect:/order/index";
    }

    @GetMapping("/over/{id}")
    public String over(@PathVariable Long id) {
        service.overOrder(id);
        return "redirect:/order/index";
    }

    @GetMapping("/distribute/{id}")
    public String distribute(@PathVariable Long id,Model model) {
        model.addAttribute("order", service.getById(id));
        return "order/distribute";
    }

    @PostMapping(value = "/distribute")
    public String distribute(@ModelAttribute Order order, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            order.setOrderState(3);
            service.updateOrder(order.getId(),order);
        }
        return "redirect:/order/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("order", service.getById(id));
        model.addAttribute("products", productsService.getAllProducts());
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
