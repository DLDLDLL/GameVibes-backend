package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gamevibe.model.entity.User;
import com.example.gamevibe.model.vo.MyPostVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.vo.UserVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

/**
* @author ZML
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2024-06-11 21:18:53
* @Entity .model.entity.User
*/

public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT u.id, u.user_id, u.nick_name, u.avatar, u.create_time, u.ip_addr, u.intro, " +
                "u.focus_count, u.fans_count, COUNT(pl.id) + COUNT(pc.id) AS like_collect_count " +
            "FROM user u " +
            "LEFT JOIN post p ON u.user_id = p.user_id AND p.is_delete = 0 " +
            "LEFT JOIN post_like pl ON p.id = pl.post_id AND pl.state = 1 " +
            "LEFT JOIN post_collect pc ON p.id = pc.post_id AND pc.state = 1 " +
            "WHERE u.user_id = #{user_id} AND u.is_delete = 0 " +
            "GROUP BY u.user_id;")
    UserVO getUserInfo(String user_id);

    @Select("SELECT COUNT(*) FROM `user` WHERE user_id = #{user_id} ")
    int isRegister(String user_id);

    @Insert("INSERT INTO `user` (user_id, avatar, nick_name) " +
            "VALUES (#{user_id}, #{avatar}, #{nick_name}) " +
            "ON DUPLICATE KEY " +
            "UPDATE avatar = COALESCE(#{avatar}, avatar), nick_name = COALESCE(#{nick_name}, nick_name)")
    void saveUser(String user_id, String avatar, String nick_name);

    @Select("SELECT p.id, p.user_id, u.nick_name, u.avatar, p.post_time, p.title, p.content, " +
                "p.images, p.type, p.comments, p.likes AS like_count, p.favours AS collect_count, " +
                "IF(pl.id IS NULL, 0, 1) AS is_like, IF(pc.id IS NULL, 0, 1) AS is_collect " +
            "FROM post p " +
            "LEFT JOIN user u on p.user_id = u.user_id AND u.is_delete = 0 " +
            "LEFT JOIN post_like pl on p.id = pl.post_id AND u.user_id = pl.user_id AND pl.state = 1 " +
            "LEFT JOIN post_collect pc on p.id = pc.post_id AND u.user_id = pc.user_id AND pc.state = 1 " +
            "WHERE p.user_id = #{user_id} AND p.is_delete = 0 " +
            "ORDER BY p.create_time DESC ")
    Page<MyPostVO> getMyPostVOPage(String user_id, Page<?> page);

}




