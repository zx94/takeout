package com.example.demo.service;

import com.example.demo.entity.OrderItems;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.mapper.OrderItemsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class OrderItemsService {

    @Autowired
    private OrderItemsMapper mapper;
    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createOrderItems(OrderItems u) {
        u.setId(idWorker.nextId());
        u.setBeenDeleted(false);
        mapper.create(u);
    }
    public void updateOrderItems(Long id,OrderItems u){
        mapper.update(id,u);
    }
    public void deleteOrderItems(Long id){
        mapper.delete(id);
    }

    public List<OrderItems> getAllOrderItems() {
        return mapper.findAll();
    }
    public OrderItems getById(Long id) {
        return mapper.findById(id);
    }

}
