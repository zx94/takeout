package com.example.demo.controller;

import com.example.demo.entity.AuthorityEnum;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.User;
import com.example.demo.service.ProductCategoryService;
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
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @Autowired
    private ProductCategoryService categoryService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("products", service.getAllProducts());
        return "product/list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllProductCategories());
        return "product/create";
    }

    @PostMapping(value = "/create")
    public String create(@ModelAttribute Product product, @RequestParam List<String> controlIdsValue, BindingResult result, Model model, HttpSession session) {
        final User user = (User) session.getAttribute(USER_SESSION_KEY);
        //@Valid注解启动后台校验,
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.createProduct(product,controlIdsValue,user);
        }
        return "redirect:/product/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("product", service.getById(id));
        model.addAttribute("categories", categoryService.getAllProductCategories());
        return "product/edit";
    }

    @PostMapping(value = "/edit")
    public String edit(@ModelAttribute Product product,@RequestParam List<String> controlIdsValue, BindingResult result, Model model, HttpSession session) {
        //@Valid注解启动后台校验,
        final User user = (User) session.getAttribute(USER_SESSION_KEY);
        if (result.hasErrors()) {
            model.addAttribute("hintMessage", "出错啦！");
        } else {
            service.updateProduct(product.getId(),product,controlIdsValue,user);
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
