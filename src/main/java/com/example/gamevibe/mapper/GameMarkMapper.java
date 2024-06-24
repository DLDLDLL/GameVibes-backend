package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.dto.GameMarkDTO;
import com.example.gamevibe.model.entity.GameMark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gamevibe.model.vo.GameMarkVO;
import com.example.gamevibe.model.vo.MyGameMarkVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @author ZML
 * @description 针对表【GameMark(游戏评分表)】的数据库操作Mapper
 * @createDate 2024-06-19 16:17:55
 * @Entity com.example.gamevibe.model.entity.GameMark
 */

public interface GameMarkMapper extends BaseMapper<GameMark> {

    @Select("SELECT gm.user_id, gm.user_avatar, gm.user_name, gm.create_time, gm.score, gm.comment " +
            "FROM game_mark gm " +
            "WHERE gm.game_id = #{game_id} AND gm.is_delete = 0 " +
            "ORDER BY gm.create_time DESC ")
    Page<GameMarkVO> getGameMarkVOPageLatest(Long game_id, Page<?> page);

    @Select("SELECT COUNT(*) FROM game_mark WHERE user_id = #{gm.user_id} AND game_id =#{gm.game_id} ")
    int isMark(@Param("gm") GameMarkDTO gameMarkDTO);

    @Insert("INSERT INTO game_mark (user_id, user_name, user_avatar, game_id, score, `comment`, image) " +
            "SELECT #{gm.user_id}, u.nick_name, u.avatar, #{gm.game_id}, #{gm.score}, #{gm.comment}, JSON_EXTRACT(g.images, '$[0]') " +
            "FROM user u " +
            "JOIN game g ON g.id = #{gm.game_id} " +
            "WHERE EXISTS (SELECT 1 FROM game WHERE id = #{gm.game_id})")
    void mark(@Param(value = "gm") GameMarkDTO gameMarkDTO);

    @Select("SELECT gm.user_id, gm.user_avatar, gm.user_name, gm.create_time, gm.score, gm.comment, gm.game_id, " +
            "g.name AS game_name, g.score AS game_score, JSON_UNQUOTE(JSON_EXTRACT(g.images, '$[0]')) AS image " +
            "FROM game_mark gm " +
            "LEFT JOIN game g ON gm.game_id = g.id AND g.is_delete = 0 " +
            "WHERE gm.user_id = #{user_id} AND gm.is_delete = 0 " +
            "ORDER BY gm.create_time DESC ")
    Page<MyGameMarkVO> getMyGameMarkVOPageLatest(String user_id, Page<?> page);


}
