package com.example.gamevibe.handler;

import com.example.gamevibe.annotation.NotResponseBody;
import com.example.gamevibe.common.ApiException;
import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ErrorCode;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = {"com.example.gamevibe.controller"})
public class GlobalExceptionHandler implements ResponseBodyAdvice<Object> {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        return new BaseResponse<String>(ErrorCode.FAILED).and(error.getDefaultMessage());
    }

    @ExceptionHandler(ApiException.class)
    public BaseResponse<String> ApiExceptionHandler(ApiException e) {
        return new BaseResponse<>(ErrorCode.FAILED, e.getMessage());
    }

    @Override
    public boolean supports(MethodParameter methodParameter, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return !(methodParameter.getParameterType().equals(BaseResponse.class) || methodParameter.hasMethodAnnotation(NotResponseBody.class));
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, @NotNull MediaType mediaType, @NotNull Class<? extends HttpMessageConverter<?>> aClass, @NotNull ServerHttpRequest serverHttpRequest, @NotNull ServerHttpResponse serverHttpResponse) {
        if (methodParameter.getGenericParameterType().equals(String.class)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(new BaseResponse<>(o));
            } catch (JsonProcessingException e) {
                throw new ApiException("返回String类型错误");
            }
        }
        return new BaseResponse<>(o);
    }
}
