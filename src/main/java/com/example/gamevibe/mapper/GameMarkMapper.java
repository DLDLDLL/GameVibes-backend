package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.dto.GameMarkDTO;
import com.example.gamevibe.model.entity.GameMark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gamevibe.model.vo.GameMarkVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
* @author ZML
* @description 针对表【GameMark(游戏评分表)】的数据库操作Mapper
* @createDate 2024-06-19 16:17:55
* @Entity com.example.gamevibe.model.entity.GameMark
*/
@Mapper
public interface GameMarkMapper extends BaseMapper<GameMark> {

    @Select("SELECT u.user_id, '' AS avatar, '' AS nick_name, gm.create_time, gm.score, gm.score " +
            "FROM game_mark gm " +
            "LEFT JOIN user u on gm.user_id = u.user_id AND u.is_delete = 0 " +
            "WHERE gm.game_id = #{game_id} AND gm.is_delete = 0 " +
            "ORDER BY gm.create_time DESC ")
    Page<GameMarkVO> getGameMarkVOPageLatest(Long game_id, Page<?> page);

    @Insert("INSERT INTO game_mark (user_id, game_id, score, `comment`) VALUES (#{gm.user_id}, #{gm.game_id}, #{gm.score}, #{gm.comment}) ")
    void mark(@Param(value = "gm") GameMarkDTO gameMarkDTO);


}




