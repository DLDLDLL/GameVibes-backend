package com.example.gamevibe.mapper;

import com.example.gamevibe.model.dto.GameDetailsDTO;
import com.example.gamevibe.model.entity.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.vo.GameRankVO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

/**
 * @author ZML
 * @description 针对表【Game(游戏表)】的数据库操作Mapper
 * @createDate 2024-06-19 16:17:55
 * @Entity com.example.gamevibe.model.entity.Game
 */

public interface GameMapper extends BaseMapper<Game> {

    @Select("SELECT id, `name`, JSON_UNQUOTE(JSON_EXTRACT(images, '$[0]')) AS image, type, score " +
            "FROM game " +
            "WHERE is_delete = 0 " +
            "ORDER BY score DESC ")
    Page<GameRankVO> getGameRankVOPage(Page<?> page);

    @Select("SELECT g.id, g.name, g.images, g.intro, g.score, g.type, IF(gm.id IS NULL, 0, gm.score) AS mark_score " +
            "FROM game g " +
            "LEFT JOIN game_mark gm on g.id = gm.game_id AND gm.user_id = #{user_id} AND gm.is_delete = 0 " +
            "WHERE g.id = #{game_id} AND g.is_delete = 0")
    GameDetailsDTO getGameDetailsDTO(Long game_id, String user_id);

    @Update("UPDATE game g SET g.score = (" +
            "SELECT ROUND(2.0 * SUM(score) / COUNT(*), 1) " +
            "FROM game_mark gm " +
            "WHERE gm.game_id = #{game_id} AND gm.is_delete = 0) " +
            "WHERE g.id = #{game_id} ")
    void updateScoreById(Long game_id);

    @Select("SELECT * FROM game WHERE update_time >= #{minUpdateTime}")
    List<Game> listGameWithDelete(Date minUpdateTime);

    List<GameRankVO> pageByIds(List<Long> gameIdList);


    List<String> listGameName(int count);
}

