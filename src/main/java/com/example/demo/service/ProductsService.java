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
    private ProductToCategoryMapper productToCategoryMapper;

    @Autowired
    private SellerService sellerService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private SnowflakeIdWorker idWorker;

    public void createProduct(Product u, List<String> categoryNames, User user) {
        u.setId(idWorker.nextId());
        u.setMerchantName(user.getUserName());
        u.setBeenDeleted(false);

        if(user.getAuthorityName().equals(AuthorityEnum.Seller.getValue())){
            u.setMerchantId(sellerService.getByUserName(user.getUserName()).getId());
        }

        List<Long> categoryIds= createNULLCategory(categoryNames,user);

        mapper.create(u);

        //中间表关联
        mapper.insertToCategory(u.getId(),categoryIds);
    }

    public void updateProduct(Long id,Product u, List<String> categoryNames, User user){
        List<Long> categoryIds= createNULLCategory(categoryNames,user);

        mapper.update(id,u);

        List<ProductToCategory> ptcs= productToCategoryMapper.findAllByProductId(id);

        for(ProductToCategory ptc : ptcs)
            productToCategoryMapper.delete(ptc.getProductId(),ptc.getCategoryId());

        mapper.insertToCategory(u.getId(),categoryIds);
    }

    public List<Long> createNULLCategory(List<String> categoryNames, User user){
        List<Long> categoryIds= null;

        for (String name:categoryNames) {
            ProductCategory productCategory = productCategoryService.getByName(name);
            if(user.getAuthorityName().equals(AuthorityEnum.Seller.getValue())) {
                productCategory.setMerchantId(sellerService.getByUserName(user.getUserName()).getId());
            }
            if(productCategory==null){
                productCategory.setId(idWorker.nextId());
                productCategoryService.createProductCategory(productCategory);
            }
            categoryIds.add(productCategory.getId());
        }
        return categoryIds;
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
