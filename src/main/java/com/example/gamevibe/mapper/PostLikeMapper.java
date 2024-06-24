package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.vo.MyPostLikeVO;
import com.example.gamevibe.model.entity.PostLike;
import org.apache.ibatis.annotations.*;

/**
 * @author ZML
 * @description 针对表【post_price(帖子点赞表)】的数据库操作Mapper
 * @createDate 2024-06-11 21:18:53
 * @Entity .model.entity.PostLike
 */

public interface PostLikeMapper extends BaseMapper<PostLike> {

//    @Select("SELECT p.id, u.user_id, u.nick_name, u.avatar, p.post_time, p.type, p.title, p.content, p.images, p.comments, " +
//                "p.likes AS like_count, p.favours AS collect_count, 1 AS is_like, " +
//                "IF(pc.id IS NULL, 0, 1) AS is_collect " +
//            "FROM post_like pl " +
//            "LEFT JOIN post p ON pl.post_id = p.id AND p.is_delete = 0 " +
//            "LEFT JOIN `user` u ON p.user_id = u.user_id AND u.is_delete = 0 " +
//            "LEFT JOIN post_collect pc on pl.post_id = pc.post_id AND pl.user_id = pc.user_id AND pc.state = 1 " +
//            "WHERE pl.user_id = #{user_id} AND pl.state = 1 " +
//            "ORDER BY pl.update_time DESC ")
    @Select("SELECT p.id, p.post_time, p.type, p.title, p.content, p.images " +
            "FROM post_like pl " +
            "LEFT JOIN post p ON pl.post_id = p.id AND p.is_delete = 0 " +
            "WHERE pl.user_id = #{user_id} AND pl.state = 1 " +
            "ORDER BY pl.update_time DESC ")
    Page<MyPostLikeVO> getLikePostVOPage(String user_id, Page<?> page);

    @Insert("INSERT INTO post_like (user_id, post_id) VALUES (#{user_id}, #{post_id}) ON DUPLICATE KEY UPDATE state = 1")
    void saveLike(String user_id, Long post_id);

    @Update("UPDATE post_like SET state = 0 WHERE user_id = #{user_id} AND post_id = #{post_id}")
    void cancelLike(String user_id, Long post_id);

    @Select("SELECT COUNT(*) FROM post_like WHERE user_id = #{user_id} AND post_id = #{post_id} AND state = 1 ")
    int isLike(String user_id, Long post_id);

    @Update("UPDATE post SET likes = likes + #{num} WHERE id = #{post_id} ")
    void updateLikes(Long post_id, Integer num);



}




