package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.vo.PostLikeVO;
import com.example.gamevibe.model.entity.PostLike;
import org.apache.ibatis.annotations.*;

/**
* @author ZML
* @description 针对表【post_price(帖子点赞表)】的数据库操作Mapper
* @createDate 2024-06-11 21:18:53
* @Entity .model.entity.PostLike
*/
@Mapper
public interface PostLikeMapper extends BaseMapper<PostLike> {


    @Select("SELECT p.id, u.user_id, u.nick_name, u.avatar, p.post_time, p.title, p.content, p.images, p.comments, pl.like_count " +
            "FROM (SELECT post_id, COUNT(*) as like_count FROM post_like WHERE state = 1 GROUP BY post_id) pl " +
            "LEFT JOIN post p ON pl.post_id = p.id " +
            "LEFT JOIN user u ON p.user_id = u.id " +
            "WHERE pl.post_id IN (SELECT post_id FROM post_like WHERE user_id = #{user_id} AND state = 1)")
    Page<PostLikeVO> getLikePostVOPage(@Param("user_id") String user_id, Page<?> page);

    @Insert("INSERT INTO post_like (user_id, post_id) VALUES (#{user_id}, #{post_id}) ON DUPLICATE KEY UPDATE state = 1")
    void saveLike(String user_id, String post_id);

    @Update("UPDATE post_like SET state = 0 WHERE user_id = #{user_id} AND post_id = #{post_id}")
    void cancelLike(String user_id, String post_id);

}




