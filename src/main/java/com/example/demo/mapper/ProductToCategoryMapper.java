package com.example.demo.mapper;

import com.example.demo.entity.ProductToCategory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductToCategoryMapper {
    void create(Long productId,Long categoryId);

    void delete(Long productId,Long categoryId);

    List<ProductToCategory> findAllByProductId(Long productId);

    List<ProductToCategory> findAllByCategoryId(Long categoryId);
}