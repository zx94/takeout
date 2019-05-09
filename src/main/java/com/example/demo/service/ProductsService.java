package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductToCategory;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.mapper.ProductToCategoryMapper;
import com.example.demo.mapper.ProductsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = {Exception.class})
public class ProductsService{

    @Autowired
    private ProductsMapper mapper;

    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createProduct(Product u,List<Long> categoryIds) {
        u.setId(idWorker.nextId());
        mapper.create(u);

        mapper.insertToCategory(u.getId(),categoryIds);
    }
    public void updateProduct(Long id,Product u){
        mapper.update(id,u);
    }
    public void deleteProduct(Long id){
        mapper.delete(id);
    }

    public List<Product> getAllProducts() {
        return mapper.findAll();
    }
    public Product getById(Long id) {
        return mapper.findById(id);
    }
    public List<Product> getByName(String Name) {
        return mapper.findByName(Name);
    }

}
