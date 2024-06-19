package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gamevibe.model.entity.User;
import com.example.gamevibe.model.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ZML
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2024-06-11 21:18:53
* @Entity .model.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

/*    @Select("SELECT u.id, u.user_id, u.avatar, u.nick_name, u.create_time, u.ip_addr, u.intro, fu.focus_count, fu.fans_count, pl.like_count + pc.collect_count AS like_collect_count " +
            "FROM (SELECT * FROM user WHERE user_id = #{user_id}) u " +
            "LEFT JOIN (SELECT user_id, COUNT(IF(user_id = #{user_id}, 1, 0)) as focus_count, COUNT(IF(focused_id = #{user_id}, 1, 0)) as fans_count FROM focus_user WHERE user_id = #{user_id} OR focused_id = #{user_id} GROUP BY user_id) fu ON fu.user_id = u.user_id " +
            "LEFT JOIN (SELECT user_id, COUNT(*) AS like_count FROM post_like WHERE post_id IN (SELECT id FROM post p WHERE p.user_id = u.id AND p.is_delete = 0) AND state = 1) pl ON pl.user_id = u.user_id " +
            "LEFT JOIN (SELECT user_id, COUNT(*) AS collect_count FROM post_collect WHERE post_id IN (SELECT id FROM post p WHERE p.user_id = u.id AND p.is_delete = 0) AND state = 1) pc ON pc.user_id = u.user_id ")*/
    UserVO getUserInfo(String user_id);

    @Insert("INSERT IGNORE INTO user (user_id) VALUES (#{user_id})")
    boolean register(String user_id);


}




