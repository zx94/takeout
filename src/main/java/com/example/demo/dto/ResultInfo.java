package com.example.demo.dto;

import lombok.Data;

//统一回传到前台的Json数据格式
@Data
public class ResultInfo {
    //状态码
    private Integer code;

    //回传的消息
    private String msg;

    //回传的数据
    private Object data;

    public ResultInfo Code(Integer code) {
        this.code = code;
        return this;
    }

    public ResultInfo Code(ResultCodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        return this;
    }

    public ResultInfo Msg(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultInfo Data(Object data) {
        this.data = data;
        return this;
    }

    //Builder 建造者模式
//    public static class Builder {
//        private Integer code;
//        private String msg;
//        private Object data;
//
//        public ResultInfo.Builder code(Integer code) {
//            this.code = code;
//            return this;
//        }
//
//        public ResultInfo.Builder msg(String msg) {
//            this.msg = msg;
//            return this;
//        }
//
//        public ResultInfo.Builder data(Object data) {
//            this.data = data;
//            return this;
//        }
//
//        public ResultInfo build() {
//            return new ResultInfo(this.code, this.msg, this.data);
//        }
//    }
}
