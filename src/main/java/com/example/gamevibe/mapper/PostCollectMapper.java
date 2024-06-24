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

public interface PostCollectMapper extends BaseMapper<PostCollect> {

    @Select("SELECT p.id, u.user_id, u.nick_name, u.avatar, p.post_time, p.type, p.title, p.content, p.images, p.comments, " +
                "p.likes AS like_count, p.favours AS collect_count, 1 AS is_collect, " +
                "IF(pl.id IS NULL, 0, 1) AS is_like " +
            "FROM post_collect pc " +
            "LEFT JOIN post p ON pc.post_id = p.id AND p.is_delete = 0 " +
            "LEFT JOIN `user` u ON p.user_id = u.user_id AND u.is_delete = 0 " +
            "LEFT JOIN post_like pl on pc.post_id = pl.post_id AND pc.user_id = pl.user_id AND pl.state = 1 " +
            "WHERE pc.user_id = #{user_id} AND pc.state = 1 " +
            "ORDER BY pc.update_time DESC ")
    Page<MyPostCollectVO> getCollectPostVOPage(String user_id, Page<?> page);

    @Insert("INSERT INTO post_collect (user_id, post_id) VALUES (#{user_id}, #{post_id}) ON DUPLICATE KEY UPDATE state = 1")
    void saveCollect(String user_id, Long post_id);

    @Update("UPDATE post_collect SET state = 0 WHERE user_id = #{user_id} AND post_id = #{post_id}")
    void cancelCollect(String user_id, Long post_id);

    @Select("SELECT COUNT(*) FROM post_collect WHERE user_id = #{user_id} AND post_id = #{post_id} AND state = 1 ")
    int isCollect(String user_id, Long post_id);

    @Update("UPDATE post SET favours = favours + #{num} WHERE id = #{post_id} ")
    void updateFavours(Long post_id, Integer num);

}