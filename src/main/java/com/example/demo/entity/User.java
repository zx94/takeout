package com.example.demo.entity;

import java.util.UUID;
import lombok.Data;

@Data
class User {
    private UUID id;

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
