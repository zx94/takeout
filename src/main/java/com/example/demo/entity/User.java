package com.example.demo.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class User {

    private Long id;

    @NotBlank(message = "用户名不能为空")
    @Column(name = "user_name")
    private String userName;

    @Column(name = "authority_name")
    private String authorityName = AuthorityEnum.Member.getValue();

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 22, message = "密码长度在6-22位之间")
    private String password;

    /**
     * 是否有效
     */
    @Column(name = "is_valid")
    private Boolean isValid;
}
