package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gamevibe.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ZML
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2024-06-11 21:18:53
* @Entity .model.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




