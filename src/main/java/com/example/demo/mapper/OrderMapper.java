package com.example.demo.mapper;

import com.example.demo.entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderMapper {
    void create(Order pc);
    void delete(Long id);
    void update(Long id, Order u);
    void pay(Long id);
    void acc(Long id);
    void over(Long id);
    void finised(Long id);

    List<Order> findAll();
    Order findById(Long id);
}