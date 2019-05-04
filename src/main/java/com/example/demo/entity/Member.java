package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Member {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 会员名称(登陆名称)
     */
    @Column(name = "member_name")
    private String memberName;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 真实名称(预留)
     */
    private String name;

    /**
     * 头像（图片url，单张）
     */
    private String avatar;

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
     * 是否有效
     */
    @Column(name = "is_valid")
    private Boolean isValid;

    /**
     * 密码
     */
    private String password;

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
     * 用户的微笑openid
     */
    private String openid;

    /**
     * wxUserInfo表中的id
     */
    @Column(name = "wx_info_id")
    private Long wxInfoId;
}