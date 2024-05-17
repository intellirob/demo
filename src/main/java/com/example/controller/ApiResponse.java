package com.example.controller;

import lombok.Data;

@Data
public class ApiResponse {

    private Integer code;

    private String msg;

    public ApiResponse(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public static ApiResponse success(){
        return new ApiResponse(0, "操作成功");
    }

}
