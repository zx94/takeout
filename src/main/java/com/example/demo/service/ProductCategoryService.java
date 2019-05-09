package com.example.demo.service;

import com.example.demo.entity.ProductCategory;
import com.example.demo.helper.SnowflakeIdWorker;
import com.example.demo.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(rollbackFor = {Exception.class})
public class ProductCategoryService{


    @Autowired
    private ProductCategoryMapper mapper;
    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createProductCategory(ProductCategory u) {
        u.setId(idWorker.nextId());
        mapper.create(u);
    }
    public void updateProductCategory(Long id,ProductCategory u){
        mapper.update(id,u);
    }
    public void deleteProductCategory(Long id){
        mapper.delete(id);
    }

    public List<ProductCategory> getAllProductCategories() {
        return mapper.findAll();
    }
    public ProductCategory getById(Long id) {
        return mapper.findById(id);
    }
    public List<ProductCategory> getByName(String Name) {
        return mapper.findByName(Name);
    }


}
