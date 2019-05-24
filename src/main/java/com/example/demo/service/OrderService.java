package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.mapper.OrderMapper;
import com.sun.org.apache.xpath.internal.operations.Or;
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
    private ProductsService productsService;

    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createOrder(OrderDto u) {
        u.getDistributeAddress();

        Product product= productsService.getByName(u.getProductName()).get(0);

        Order order=new Order();
        order.setDistributeAddress(u.getDistributeAddress());
        order.setMemberMobile(u.getMemberMobile());
        order.setDistributeAddress(u.getDistributeAddress());
        order.setId(idWorker.nextId());

        order.setTotalAmount(product.getPrice());
        order.setOrderState(0);
        order.setBeenDeleted(false);
        mapper.create(order);
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

    public void payOrder(Long id) {
        mapper.pay(id);
    }

    public void accOrder(Long id) {
        mapper.acc(id);
    }

    public void overOrder(Long id) {
        mapper.over(id);
    }

}
