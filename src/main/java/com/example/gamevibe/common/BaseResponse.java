package com.example.gamevibe.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应实体类
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }

    public BaseResponse(ErrorCode errorCode, T data) {
        code = errorCode.getCode();
        message = errorCode.getMessage();
        this.data = data;
    }

    public BaseResponse(T data) {
        this(ErrorCode.SUCCESS, data);
    }

    public BaseResponse<T> and(String message) {
        this.message = this.message.concat(",").concat(message);
        return this;
    }

}

