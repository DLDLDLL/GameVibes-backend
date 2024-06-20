package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.UserMapper;
import com.example.gamevibe.model.entity.User;
import com.example.gamevibe.model.vo.UserVO;
import com.example.gamevibe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ZML
 * @description 针对表【user(用户信息表)】的数据库操作Service实现
 * @createDate 2024-06-11 21:18:53
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserVO getUserInfo() {
        String user_id = BaseContext.getCurrentId();
        return userMapper.getUserInfo(user_id);
    }

    @Override
    public void register(String avatar) {
        String user_id = BaseContext.getCurrentId();
        userMapper.register(user_id, avatar);
    }


}




