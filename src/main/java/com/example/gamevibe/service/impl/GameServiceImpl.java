package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.GameMapper;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.Game;
import com.example.gamevibe.model.vo.GameDetailsVO;
import com.example.gamevibe.model.vo.GameRankVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author ZML
* @description 针对表【Game(游戏表)】的数据库操作Service实现
* @createDate 2024-06-19 16:17:55
*/
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {

    @Autowired
    private GameMapper gameMapper;

    @Override
    public PageVO<GameRankVO> getGameVOPage(PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        Page<GameRankVO> gameVOPage = gameMapper.getGameVOPage(new Page<>(current, size));
        return new PageVO<GameRankVO>().objToVO(gameVOPage);
    }

    @Override
    public GameDetailsVO getGameDetailsVO(Long game_id) {
        String user_id = BaseContext.getCurrentId();
        return gameMapper.getGameDetailsVO(game_id, user_id);
    }



}




