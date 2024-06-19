package com.example.gamevibe.aspect;

import com.example.gamevibe.model.dto.PageRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class FillPageAspect {

    @Around("execution(* com.example.gamevibe.controller.*.*(..)) && args(pageRequest, ..)")
    public Object fillPageRequest(ProceedingJoinPoint joinPoint, PageRequest pageRequest) throws Throwable {
        if (pageRequest == null) pageRequest = new PageRequest();
        Object[] args = joinPoint.getArgs();
        args[0] = pageRequest;
        return joinPoint.proceed(args);
    }




}
