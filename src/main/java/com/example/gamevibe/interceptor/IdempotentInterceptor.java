package com.example.gamevibe.interceptor;

import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.context.MessageContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class IdempotentInterceptor implements HandlerInterceptor {

    private static final Map<String, String> parameterMap = Map.of(
            "/api/post_like/like", "post_id",
            "/api/post_collect/collect", "post_id",
            "/api/focus_user/focus", "focus_id"
    );

    @Resource
    MessageContext messageContext;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String path = request.getRequestURI();
        String param = parameterMap.get(path);
        String arg = request.getParameter(param);
        boolean check = messageContext.getFocusStrategy(path).check(arg);
        if (check) {
            ResultUtils.writeResultToResponse(response, ResultUtils.success());
            return false;
        }
        return true;
    }
}
