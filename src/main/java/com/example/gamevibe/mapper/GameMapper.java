package com.example.gamevibe.mapper;

import com.example.gamevibe.model.entity.Game;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.vo.GameDetailsVO;
import com.example.gamevibe.model.vo.GameRankVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
* @author ZML
* @description 针对表【Game(游戏表)】的数据库操作Mapper
* @createDate 2024-06-19 16:17:55
* @Entity com.example.gamevibe.model.entity.Game
*/
@Mapper
public interface GameMapper extends BaseMapper<Game> {

    @Select("SELECT id, `name`, images, type, score FROM game WHERE is_delete = 0 ORDER BY score DESC ")
    Page<GameRankVO> getGameVOPage(Page<?> page);

    @Select("SELECT g.id, g.name, g.images, g.intro, g.score, g.type, IF(gm.id IS NULL, 0, 1) AS is_mark " +
            "FROM game g " +
            "LEFT JOIN game_mark gm on g.id = gm.game_id AND gm.user_id = #{user_id} AND gm.is_delete = 0 " +
            "WHERE g.id = #{game_id} AND g.is_delete = 0")
    GameDetailsVO getGameDetailsVO(Long game_id, String user_id);


}




