package com.example.demo.dto;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class OrderDto {
    /**
     * 描述
     */
    private String description;

    /**
     * 配送地址
     */
    @Column(name = "distribute_address")
    private String distributeAddress;

    /**
     * 会员名称
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 联系电话
     */
    @Column(name = "member_mobile")
    private String memberMobile;

    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 配送员名称
     */
    @Column(name = "distributer_name")
    private String distributerName;

    /**
     * 配送员联系电话
     */
    @Column(name = "distributer_mobile")
    private String distributerMobile;

    /**
     * 配送费
     */
    @Column(name = "distribute_price")
    private BigDecimal distributePrice;

    /**
     * 优惠金额
     */
    private BigDecimal discount;

    /**
     * 打包费
     */
    @Column(name = "pack_price")
    private BigDecimal packPrice;

    /**
     * 合计金额
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;
}
