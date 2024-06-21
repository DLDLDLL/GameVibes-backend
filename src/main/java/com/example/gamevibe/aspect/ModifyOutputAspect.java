package com.example.gamevibe.aspect;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.UserMapper;
import com.example.gamevibe.model.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Slf4j
@Aspect
@Component
public class ModifyOutputAspect {

    @Autowired
    private UserMapper userMapper;

    @Pointcut("execution(* com.example.gamevibe.service.UserService.getUserInfo(..))")
    public void getUserInfo() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.GameService.getGameDetailsVO(..))")
    public void getGameDetails() {
    }

    @Around("getUserInfo()")
    public Object fillUserInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        this.saveUser();
        UserVO userVO = (UserVO) joinPoint.proceed();
        userVO.setNick_name(BaseContext.getCurrentName());
        userVO.setAvatar(BaseContext.getCurrentAvatar());
        return userVO;
    }

    private void saveUser() {
        String user_id = BaseContext.getCurrentId();
        String avatar = BaseContext.getCurrentAvatar();
        String nick_name = BaseContext.getCurrentName();
        userMapper.saveUser(user_id, avatar, nick_name);
    }

    @Around("getGameDetails()")
    public Object modifyGameDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        GameDetailsVO gameDetailsVO = (GameDetailsVO) joinPoint.proceed();
        gameDetailsVO.processImages();
        return gameDetailsVO;
    }


}
