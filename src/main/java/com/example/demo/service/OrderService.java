package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class OrderService{


    @Autowired
    private OrderMapper mapper;
    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createOrder(Order u) {
        u.setId(idWorker.nextId());
        mapper.create(u);
    }
    public void updateOrder(Long id,Order u){
        mapper.update(id,u);
    }
    public void deleteOrder(Long id){
        mapper.delete(id);
    }

    public List<Order> getAllOrders() {
        return mapper.findAll();
    }
    public Order getById(Long id) {
        return mapper.findById(id);
    }

}
