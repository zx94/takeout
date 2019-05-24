package com.example.demo.dto;

//消息码
public enum ResultCodeEnum {

    SUCCESS(1),

    FAIL(0);

    Integer code;

    ResultCodeEnum(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
