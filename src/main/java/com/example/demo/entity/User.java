package com.example.demo.entity;

import java.util.UUID;
import lombok.Data;
import com.example.demo.helper.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class User {
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;

    private long id;

    private String userName;

    private String authority;

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
