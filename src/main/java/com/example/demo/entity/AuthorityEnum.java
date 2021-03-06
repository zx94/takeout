package com.example.demo.entity;

public enum AuthorityEnum {
    Admin("管理员", "Admin"),
    Seller("商家", "Seller"),
    Member("客户", "Member");

    private String text;
    private String value;

    AuthorityEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}


