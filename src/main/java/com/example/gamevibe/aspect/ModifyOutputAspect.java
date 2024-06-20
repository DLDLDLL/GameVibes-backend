package com.example.gamevibe.aspect;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ModifyOutputAspect {

    @Pointcut("execution(* com.example.gamevibe.service.UserService.getUserInfo(..))")
    public void getUserInfo() {
    }

    @Around("getUserInfo()")
    public Object fillUserInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        UserVO userVO = (UserVO) joinPoint.proceed();
        userVO.setNick_name(BaseContext.getCurrentName());
        userVO.setAvatar(BaseContext.getCurrentAvatar());
        return userVO;
    }


}
