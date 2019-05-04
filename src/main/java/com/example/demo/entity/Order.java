package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Order {
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
     * 描述
     */
    private String description;

    /**
     * 订单状态（-1：已取消, 0：未支付，1：已支付；2：已接单，3: 配送中，4: 已完成）
     */
    @Column(name = "order_state")
    private Integer orderState;

    /**
     * 配送地址
     */
    @Column(name = "distribute_address")
    private String distributeAddress;

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
     * 会员名称
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 会员id
     */
    @Column(name = "member_id")
    private Long memberId;

    /**
     * 联系电话
     */
    @Column(name = "member_mobile")
    private String memberMobile;

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

    /**
     * 配送员名称
     */
    @Column(name = "distributer_name")
    private String distributerName;

    /**
     * 配送员id
     */
    @Column(name = "distributer_id")
    private Long distributerId;

    /**
     * 配送员联系电话
     */
    @Column(name = "distributer_mobile")
    private String distributerMobile;

    /**
     * 配送单位名称
     */
    @Column(name = "distribution_name")
    private String distributionName;

    /**
     * 配送单位id
     */
    @Column(name = "distribution_id")
    private Long distributionId;

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
    private Boolean beenDeleted;
}