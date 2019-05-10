package com.example.demo.service;

import com.example.demo.entity.*;
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
    private SellerService sellerService;

    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createProduct(Product u, List<Long> categoryIds, User user) {
        u.setId(idWorker.nextId());
        u.setMerchantName(user.getUserName());

        if(user.getAuthorityName()== AuthorityEnum.Seller.getValue()){
            u.setMerchantId(sellerService.getByUserName(user.getUserName()).getId());
        }

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

    /***
     * 用户通过店铺查找所有产品
     * @param sellerId
     * @return
     */
    public List<Product> getAllProductsBySeller(Long sellerId) {
        return mapper.findAllBySellerId(sellerId);
    }

    /***
     * 商家只能查找到自己的所有产品
     * @param user
     * @return
     */
    public List<Product> getAllProductsBySeller(User user) {
        Seller seller= sellerService.getByUserName(user.getUserName());
        return mapper.findAllBySellerId(seller.getId());
    }

    public Product getById(Long id) {
        return mapper.findById(id);
    }

    public List<Product> getByName(String Name) {
        return mapper.findByName(Name);
    }

}
