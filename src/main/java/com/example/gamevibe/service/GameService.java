package com.example.gamevibe.service;

import com.example.gamevibe.model.dto.GameDetailsDTO;
import com.example.gamevibe.model.dto.GameQueryRequest;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.Game;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.vo.*;

import java.io.IOException;
import java.util.List;

/**
 * @author ZML
 * @description 针对表【Game(游戏表)】的数据库操作Service
 * @createDate 2024-06-19 16:17:55
 */
public interface GameService extends IService<Game> {

    PageVO<GameRankVO> getGameRankVOPage(PageRequest pageRequest);

    GameDetailsDTO getGameDetailsVO(Long game_id);

    PageVO<GameRankVO> searchFromEs(GameQueryRequest gameQueryRequest) throws IOException;

    List<String> listGameName(Integer count);
}
