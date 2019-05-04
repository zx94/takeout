package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Product implements Serializable{
	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 商品名称
	 */
	private String name;

	/**
	 * 商品价格
	 */
	private BigDecimal price;

	/**
	 * 商品库存
	 */
	@Column(name = "in_stocks")
	private Integer inStocks;

	/**
	 * 销量
	 */
	@Column(name = "sales_volume")
	private Integer salesVolume;

	/**
	 * 月销量
	 */
	@Column(name = "sales_month_volume")
	private Integer salesMonthVolume;

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
	 * 分类id
	 */
	@Column(name = "category_id")
	private Long categoryId;

	/**
	 * 分类名称
	 */
	@Column(name = "category_name")
	private String categoryName;

	/**
	 * 商品图片
	 */
	private String images;
}
