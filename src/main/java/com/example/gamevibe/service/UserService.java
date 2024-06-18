package com.example.gamevibe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.entity.User;
import com.example.gamevibe.model.vo.UserVO;

/**
* @author ZML
* @description 针对表【user(用户信息表)】的数据库操作Service
* @createDate 2024-06-11 21:18:53
*/
public interface UserService extends IService<User> {

    UserVO getUserInfo();
}
