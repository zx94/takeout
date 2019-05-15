package com.example.demo.controller;

import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.User;
import com.example.demo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.example.demo.helper.SysConst.USER_SESSION_KEY;

@Controller
@RequestMapping("/category")
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService service;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("productCategories", service.getAllProductCategories());
        return "/category/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("productCategory", new ProductCategory());
        return "/category/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute ProductCategory productCategory, BindingResult result, Model model, HttpSession session) {
        //@Valid注解启动后台校验,
        final User user = (User) session.getAttribute(USER_SESSION_KEY);
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.createProductCategory(productCategory,user);
        }
        return "redirect:/category/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("productCategory", service.getById(id));
        return "/category/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute ProductCategory productCategory, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.updateProductCategory(productCategory.getId(),productCategory);
        }
        return "redirect:/category/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        service.deleteProductCategory(id);
        return "redirect:/category/index";
    }

}
