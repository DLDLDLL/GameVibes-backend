package com.example.gamevibe.aspect;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.FocusUserMapper;
import com.example.gamevibe.mapper.UserMapper;
import com.example.gamevibe.model.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Slf4j
@Aspect
@Component
public class ModifyOutputAspect {

    @Resource
    UserMapper userMapper;

    @Resource
    FocusUserMapper focusUserMapper;

    @Pointcut("execution(* com.example.gamevibe.service.UserService.getUserInfo(..))")
    public void getUserInfo() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.GameService.getGameDetailsVO(..))")
    public void getGameDetails() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.CollectMessageService.listCollectMessage(..))")
    public void listCollectMessage() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.LikeMessageService.listLikeMessage(..))")
    public void listLikeMessage() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.FocusUserService.getFocusUserVOPage(..))")
    public void pageFocus() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.FocusUserService.getFansVOPage(..))")
    public void pageFans() {
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

    @Around("listCollectMessage()")
    public Object fillCollectMessage(ProceedingJoinPoint joinPoint) throws Throwable {
        List<CollectMessageVO> collectMessageVOS = (List<CollectMessageVO>) joinPoint.proceed();
        String name = BaseContext.getCurrentName();
        String avatar = BaseContext.getCurrentAvatar();
        collectMessageVOS.forEach(cmvo -> {
            cmvo.setPost_user_avatar(avatar);
            cmvo.setPost_user_name(name);
        });
        return collectMessageVOS;
    }

    @Around("listLikeMessage()")
    public Object fillLikeMessage(ProceedingJoinPoint joinPoint) throws Throwable {
        List<LikeMessageVO> likeMessageVOS = (List<LikeMessageVO>) joinPoint.proceed();
        String name = BaseContext.getCurrentName();
        String avatar = BaseContext.getCurrentAvatar();
        likeMessageVOS.forEach(lmvo -> {
            lmvo.setPost_user_avatar(avatar);
            lmvo.setPost_user_name(name);
        });
        return likeMessageVOS;
    }

    @Around("pageFocus()")
    public Object fillPageFocus(ProceedingJoinPoint joinPoint) throws Throwable {
        PageVO<FocusUserVO> focusUserVOPage = (PageVO<FocusUserVO>) joinPoint.proceed();
        String current_user_id = BaseContext.getCurrentId();
        List<FocusUserVO> records = focusUserVOPage.getRecords();
        String user_id = joinPoint.getArgs()[1].toString();
        if (current_user_id.equals(user_id))
            records.forEach(record -> record.setIs_focus(1));
        else {
            List<String> fids = focusUserMapper.getFids(current_user_id);
            Set<String> set = new HashSet<>(fids);
            records.forEach(record -> {
                if (set.contains(record.getUser_id())) record.setIs_focus(1);
            });
        }
        focusUserVOPage.setRecords(records);
        return focusUserVOPage;
    }

    @Around("pageFans()")
    public Object fillPageFans(ProceedingJoinPoint joinPoint) throws Throwable {
        PageVO<FocusUserVO> fansVOPage = (PageVO<FocusUserVO>) joinPoint.proceed();
        String current_user_id = BaseContext.getCurrentId();
        List<FocusUserVO> records = fansVOPage.getRecords();
        List<String> fids = focusUserMapper.getFids(current_user_id);
        Set<String> set = new HashSet<>(fids);
        records.forEach(record -> {
            if (set.contains(record.getUser_id())) record.setIs_focus(1);
        });
        fansVOPage.setRecords(records);
        return fansVOPage;
    }


}
