package com.example.demo.controller;

import com.example.demo.entity.AuthorityEnum;
import com.example.demo.entity.Product;
import com.example.demo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", service.getAllProducts());
        return "product/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("authorityNames", AuthorityEnum.values());
        return "product/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Product product, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.createProduct(product);
        }
        return "redirect:/product/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("product", service.getById(id));
        return "product/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute Product product, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.updateProduct(product.getId(),product);
        }
        return "redirect:/product/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        service.deleteProduct(id);
        return "redirect:/product/index";
    }
}
