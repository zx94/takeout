package com.example.demo.mapper;

import com.example.demo.entity.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductCategoryMapper{
    void create(ProductCategory pc);
    void delete(Long id);
    void update(Long id, ProductCategory u);

    List<ProductCategory> findAll();
    ProductCategory findById(Long id);
    ProductCategory findByName(String name);

    void insertToProduct(Long categoryId, List<Long> productIds);

    List<ProductCategory> findAllByProduct(List<Long> productIds);
}