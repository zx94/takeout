package com.example.demo.service;

import com.example.demo.entity.Seller;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.mapper.SellerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = {Exception.class})
public class SellerService{
    @Autowired
    private SellerMapper mapper;
    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createSeller(Seller u) {
        u.setId(idWorker.nextId());
        mapper.create(u);
    }
    public void updateSeller(Long id,Seller u){
        mapper.update(id,u);
    }
    public void deleteSeller(Long id){
        mapper.delete(id);
    }

    public List<Seller> getAllSellers() {
        return mapper.findAll();
    }
    public Seller getById(Long id) {
        return mapper.findById(id);
    }
    public List<Seller> getByName(String Name) {
        return mapper.findByName(Name);
    }

    public Seller getByUserName(String userName) {
        return mapper.findByUserName(userName);
    }
}
