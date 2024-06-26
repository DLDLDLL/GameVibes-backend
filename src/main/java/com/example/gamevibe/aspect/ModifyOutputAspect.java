package com.example.gamevibe.aspect;

import cn.hutool.json.JSONUtil;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.FocusUserMapper;
import com.example.gamevibe.mapper.UserMapper;
import com.example.gamevibe.model.dto.GameDetailsDTO;
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

    @Pointcut("execution(* com.example.gamevibe.service.PostLikeService.getLikePostVOPage(..))")
    public void listPostLike() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.PostCollectService.getCollectPostVOPage(..))")
    public void listPostCollect() {
    }

    @Pointcut("execution(* com.example.gamevibe.service.UserService.getMyPostVOPage(..))")
    public void listPost() {
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
        GameDetailsDTO gameDetailsDTO = (GameDetailsDTO) joinPoint.proceed();
        return new GameDetailsVO().DTOToVO(gameDetailsDTO);
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

    @Around("listPostLike()")
    public Object postLikeJsonToList(ProceedingJoinPoint joinPoint) throws Throwable {
        PageVO<MyPostLikeVO> postLikePage = (PageVO<MyPostLikeVO>) joinPoint.proceed();
        List<MyPostLikeVO> records = postLikePage.getRecords();
        records.forEach(record -> record.setImageList(JSONUtil.toList(record.getImages(), String.class)));
        postLikePage.setRecords(records);
        return postLikePage;
    }

    @Around("listPostCollect()")
    public Object postCollectJsonToList(ProceedingJoinPoint joinPoint) throws Throwable {
        PageVO<MyPostCollectVO> postCollectPage = (PageVO<MyPostCollectVO>) joinPoint.proceed();
        List<MyPostCollectVO> records = postCollectPage.getRecords();
        records.forEach(record -> record.setImageList(JSONUtil.toList(record.getImages(), String.class)));
        postCollectPage.setRecords(records);
        return postCollectPage;
    }

    @Around("listPost()")
    public Object postJsonToList(ProceedingJoinPoint joinPoint) throws Throwable {
        PageVO<MyPostVO> postPage = (PageVO<MyPostVO>) joinPoint.proceed();
        List<MyPostVO> records = postPage.getRecords();
        records.forEach(record -> record.setImageList(JSONUtil.toList(record.getImages(), String.class)));
        postPage.setRecords(records);
        return postPage;
    }


}
