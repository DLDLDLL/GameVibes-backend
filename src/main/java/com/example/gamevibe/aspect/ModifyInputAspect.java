package com.example.gamevibe.aspect;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.model.dto.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ModifyInputAspect {

    @Around("execution(* com.example.gamevibe.controller.*.*(..)) && args(pageRequest, ..)")
    public Object fillPageRequest(ProceedingJoinPoint joinPoint, PageRequest pageRequest) throws Throwable {
        if (pageRequest == null) pageRequest = new PageRequest();
        Object[] args = joinPoint.getArgs();
        args[0] = pageRequest;
        return joinPoint.proceed(args);
    }

    @Around("execution(* com.example.gamevibe.controller.*.*(..)) && args(.., user_id)")
    public Object fillUserId(ProceedingJoinPoint joinPoint, String user_id) throws Throwable {
        if (StringUtils.isBlank(user_id)) user_id = BaseContext.getCurrentId();
        Object[] args = joinPoint.getArgs();
        args[1] = user_id;
        return joinPoint.proceed(args);
    }




}
