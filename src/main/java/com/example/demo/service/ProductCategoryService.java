package com.example.demo.service;

import com.example.demo.entity.AuthorityEnum;
import com.example.demo.entity.ProductCategory;
import com.example.demo.entity.Seller;
import com.example.demo.entity.User;
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
    private SellerService sellerService;

    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createProductCategory(ProductCategory u, User user) {
        if(user.getAuthorityName().equals(AuthorityEnum.Seller.getValue())) {
            u.setMerchantId(sellerService.getByUserName(user.getUserName()).getId());
        }

        createProductCategory(u);
    }

    public void createProductCategory(ProductCategory u) {
        u.setId(idWorker.nextId());
        u.setBeenDeleted(false);

        if(u.getMerchantId()!=null && u.getMerchantName().isEmpty()){
            Seller seller= sellerService.getById(u.getMerchantId());
            u.setMerchantName(seller.getName());
        }

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
    public ProductCategory getByName(String Name) {
        return mapper.findByName(Name);
    }

}
