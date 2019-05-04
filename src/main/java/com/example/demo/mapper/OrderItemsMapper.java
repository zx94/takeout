package com.example.demo.mapper;

import com.example.demo.entity.OrderItems;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderItemsMapper {
    void create(OrderItems orderItems);
    void delete(Long id);
    void update(Long id, OrderItems u);

    List<OrderItems> findAll();
    OrderItems findById(Long id);
    List<OrderItems> findByName(String name);
}