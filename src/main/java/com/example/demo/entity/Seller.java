package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Seller {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 商户名称
     */
    private String name;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 负责人
     */
    @Column(name = "principal_name")
    private String principalName;

    /**
     * 负责人电话
     */
    @Column(name = "principal_mobile")
    private String principalMobile;

    /**
     * 营业时间(开始时间）
     */
    @Column(name = "start_hours")
    private String startHours;

    /**
     * 营业时间(结束时间)
     */
    @Column(name = "end_hours")
    private String endHours;

    /**
     * 所属省份（000001）
     */
    private String province;

    /**
     * 所属市（000001001）
     */
    private String city;

    /**
     * 所属区（000001001001）
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 门店Logo（图片url，单张）
     */
    private String logo;

    /**
     * 门店外景照片（url,url,url）
     */
    @Column(name = "out_image")
    private String outImage;

    /**
     * 门店内景照片（url,url,url）
     */
    @Column(name = "inner_image")
    private String innerImage;

    /**
     * 身份证正面照
     */
    @Column(name = "id_card_front_image")
    private String idCardFrontImage;

    /**
     * 身份证反面照
     */
    @Column(name = "id_card_back_image")
    private String idCardBackImage;

    /**
     * 营业执照（图片上传）
     */
    @Column(name = "business_license")
    private String businessLicense;

    /**
     * 行业许可证
     */
    @Column(name = "industry_license")
    private String industryLicense;

    /**
     * 审核状态（0：未审核，1：通过；2：不通过）
     */
    private Integer state;

    /**
     * 是否有效
     */
    @Column(name = "is_valid")
    private Boolean isValid;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 描述
     */
    private String description;

    /**
     * 登陆账号
     */
    @Column(name = "login_account")
    private String loginAccount;

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
    private Boolean beenDeleted = false;
}