package com.example.demo.entity;

import java.util.UUID;
import lombok.Data;
import com.example.demo.helper.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class User {
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    private long id;

    @NotBlank(message = "用户名不能为空")
    private String userName;

    private String authority;

    @NotBlank(message = "密码不能为空")
    @Size(min=6,max=22,message = "密码长度在6-22位之间")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }
}
