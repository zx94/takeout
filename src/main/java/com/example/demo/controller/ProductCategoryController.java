package com.example.demo.controller;

import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.Seller;
import com.example.demo.service.ProductCategoryService;
import com.example.demo.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/category")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService service;

    @Autowired
    private ProductsService productsService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("productCategory", service.getAllProductCategories());
        return "/product/category/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("productCategory", new ProductCategory());
        return "/product/category/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute ProductCategory productCategory, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.createProductCategory(productCategory);
        }
        return "redirect:/product/category/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("seller", service.getById(id));
        return "/product/category/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute ProductCategory productCategory, BindingResult result, Model model) {
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.updateProductCategory(productCategory.getId(),productCategory);
        }
        return "redirect:/product/category/index";
    }

    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        service.deleteProductCategory(id);
        return "redirect:/product/category/index";
    }

}
