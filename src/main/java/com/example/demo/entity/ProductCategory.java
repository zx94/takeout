package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
@Table(name = "product_category")
public class ProductCategory {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 审核状态（0：未审核，1：通过；2：不通过）
     */
    private Integer state;

    /**
     * 商户名称
     */
    @Column(name = "merchant_name")
    private String merchantName;

    /**
     * 商户id
     */
    @Column(name = "merchant_id")
    private Long merchantId;

    /**
     * 数据入库时间
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
    private Boolean beenDeleted;

    /**
     * 分类图片
     */
    private String images;
}