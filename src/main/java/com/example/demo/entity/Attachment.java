package com.example.demo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

//附件
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "blog_attachment")
public class Attachment {

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * url
     */
    private String url;

    /**
     *  缩略图url
     */
    @Column(name = "thumbnail_url")
    private String thumbnailUrl;

    /**
     * 类型
     */
    private String type;

    /**
     * 后缀
     */
    private String suffix;

    /**
     * 大小
     */
    private String size;

    /**
     * 长宽
     */
    @Column(name = "length_and_width")
    private String lengthAndWidth;

    /**
     * 存储位置（本地 或者 云）
     */
    private String location;

    /**
     *  来源
     *  0:上传
     *  1:外链
     */
    private Integer origin = 0;

    /**
     * 创建时间 自动更新
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     *  创建人
     */
    @Column(name = "create_user")
    private String createUser;

    /**
     *  最后更新人
     */
    @Column(name = "update_user")
    private String updateUser;

    /**
     *  是否被删除 默认为false
     */
    @Column(name = "been_deleted")
    private boolean beenDeleted = false;
}