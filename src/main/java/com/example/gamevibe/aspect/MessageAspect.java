package com.example.gamevibe.aspect;

import com.example.gamevibe.context.MessageContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Slf4j
@Aspect
@Component
public class MessageAspect {

    @Resource
    MessageContext messageContext;

    @Pointcut("execution(* com.example.gamevibe.service.PostLikeService.like(..))")
    public void like() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.PostCollectService.collect(..))")
    public void collect() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.FocusUserService.focus(..))")
    public void focus() {
    }

    @Around("like() || collect() || focus()")
    public Object send(ProceedingJoinPoint joinPoint) throws Throwable {
        Object data = joinPoint.proceed();
        String name = joinPoint.getSignature().getName();
        Object arg = joinPoint.getArgs()[0];
        messageContext.getSendStrategy(name).send(arg);
        return data;
    }


    @Pointcut("execution(* com.example.gamevibe.service.LikeMessageService.listLikeMessage(..))")
    public void listLikeMessage() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.CollectMessageService.listCollectMessage(..))")
    public void listCollectMessage() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.FocusUserMessageService.listFocusUserMessage(..))")
    public void listFocusUserMessage() {
    }

    @Around("listLikeMessage() || listCollectMessage() || listFocusUserMessage()")
    public Object accept(ProceedingJoinPoint joinPoint) throws Throwable {
        Object data = joinPoint.proceed();
        String name = joinPoint.getSignature().getName();
        messageContext.getAcceptStrategy(name).accept(data);
        return data;
    }


}
