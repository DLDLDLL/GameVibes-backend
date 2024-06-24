package com.example.gamevibe.common;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private Integer code;

    private String message;

    public ApiException() {
        this(1001, "接口错误");
    }

    public ApiException(String message) {
        this(1001, message);
    }

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

}
