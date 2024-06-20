package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.vo.MyPostCollectVO;
import com.example.gamevibe.model.entity.PostCollect;
import org.apache.ibatis.annotations.*;

/**
 * @author ZML
 * @description 针对表【post_collect(帖子收藏表)】的数据库操作Mapper
 * @createDate 2024-06-11 21:18:53
 * @Entity .model.entity.PostCollect
 */
@Mapper
public interface PostCollectMapper extends BaseMapper<PostCollect> {

    @Select("SELECT p.id, u.user_id, u.nick_name, u.avatar, p.post_time, p.type, p.title, p.content, p.images, p.comments, pl.like_count, pc.collect_count, pl.is_like, 1 AS is_collect " +
            "FROM (SELECT post_id FROM post_collect WHERE user_id = #{user_id} AND state = 1) pcu " +
            "LEFT JOIN post p ON pcu.post_id = p.id AND p.is_delete = 0 " +
            "LEFT JOIN (SELECT post_id, COUNT(*) as like_count, SUM(IF(user_id = #{user_id} AND state = 1, 1, 0)) as is_like FROM post_like WHERE state = 1 GROUP BY post_id) pl ON p.id = pl.post_id " +
            "LEFT JOIN (SELECT post_id, COUNT(*) as collect_count FROM post_collect WHERE state = 1 GROUP BY post_id) pc ON p.id = pc.post_id  " +
            "LEFT JOIN user u ON p.user_id = u.id ")
    Page<MyPostCollectVO> getCollectPostVOPage(String user_id, Page<?> page);

    @Insert("INSERT INTO post_collect (user_id, post_id) VALUES (#{user_id}, #{post_id}) ON DUPLICATE KEY UPDATE state = 1")
    void saveCollect(String user_id, Long post_id);

    @Update("UPDATE post_collect SET state = 0 WHERE user_id = #{user_id} AND post_id = #{post_id}")
    void cancelCollect(String user_id, Long post_id);

    @Select("SELECT COUNT(*) FROM post_collect WHERE user_id = #{user_id} AND post_id = #{post_id} AND state = 1 ")
    int isCollect(String user_id, Long post_id);

}