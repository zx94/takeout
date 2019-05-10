package com.example.demo.mapper;

import com.example.demo.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductsMapper{
    void create(Product product);
    void delete(Long id);
    void update(Long id, Product u);

    List<Product> findAll();

    Product findById(Long id);

    List<Product> findByName(String name);

    void insertToCategory(Long productId, List<Long> categoryIds);

    List<Product> findAllByCategory(List<Long> categoryIds);

    List<Product> findAllBySellerId(Long sellerId);

}