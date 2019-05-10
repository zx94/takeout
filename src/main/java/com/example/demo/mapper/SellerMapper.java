package com.example.demo.mapper;

import com.example.demo.entity.Seller;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SellerMapper {
    void create(Seller seller);
    void delete(Long id);
    void update(Long id, Seller u);

    List<Seller> findAll();
    Seller findById(Long id);
    List<Seller> findByName(String name);

    Seller findByUserName(String userName);
}