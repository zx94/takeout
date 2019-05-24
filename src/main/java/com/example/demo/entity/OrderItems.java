package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "order_items")
public class OrderItems {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 订单号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Long orderId;

    /**
     * 商品名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 商品id
     */
    @Column(name = "product_id")
    private Long productId;

    /**
     * 商品价格(原价)
     */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /**
     * 商品优惠金额
     */
    @Column(name = "product_discount")
    private BigDecimal productDiscount;

    /**
     * 描述
     */
    private String description;

    /**
     * 图片
     */
    private String image;

    /**
     * 数据入库时间(订单时间)
     */
    @Column(name = "insert_time")
    private Date insertTime;

    /**
     * 数据最后一次更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    private Date deleteTime;

    /**
     * 是否已删除
     */
    @Column(name = "been_deleted")
    private Boolean beenDeleted = false;
}