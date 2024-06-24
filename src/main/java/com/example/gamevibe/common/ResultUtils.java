package com.example.gamevibe.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 返回结果工具类
 */
public class ResultUtils {

    private static final Logger logger = LoggerFactory.getLogger(ResultUtils.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    public static <T> BaseResponse<T> success() {
        return success(null);
    }

    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse error(int code, String message) {
        return new BaseResponse(code, null, message);
    }

    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message);
    }

    public static void error(HttpServletResponse response, String message) {
        BaseResponse baseResponse = new BaseResponse(500, message, null);
        writeResultToResponse(response, baseResponse);
    }

    public static BaseResponse error(String message) {
        BaseResponse response = new BaseResponse();
        response.setMessage(message);
        response.setCode(0);
        return response;
    }

    public static void writeResultToResponse(HttpServletResponse response, BaseResponse baseResponse) {
        try {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String json = objectMapper.writeValueAsString(baseResponse);
            writer.write(json);
            writer.flush();
        } catch (Exception e) {
            logger.error("failed to write to the response stream", e);
        }
    }

}
