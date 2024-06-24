package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.GameMapper;
import com.example.gamevibe.mapper.GameMarkMapper;
import com.example.gamevibe.model.dto.GameMarkDTO;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.GameMark;
import com.example.gamevibe.model.vo.GameMarkVO;
import com.example.gamevibe.model.vo.MyGameMarkVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.GameMarkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author ZML
 * @description 针对表【GameMark(游戏评分表)】的数据库操作Service实现
 * @createDate 2024-06-19 16:17:55
 */
@Service
public class GameMarkServiceImpl extends ServiceImpl<GameMarkMapper, GameMark> implements GameMarkService {

    @Resource
    private GameMarkMapper gameMarkMapper;

    @Resource
    private GameMapper gameMapper;

    @Override
    public PageVO<GameMarkVO> getGameMarkVOPage(PageRequest pageRequest, Long game_id) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        Page<GameMarkVO> gameMarkVOPage = gameMarkMapper.getGameMarkVOPageLatest(game_id, new Page<>(current, size));
        return new PageVO<GameMarkVO>().objToVO(gameMarkVOPage);
    }

    @Override
    public void mark(GameMarkDTO gameMarkDTO) {
        gameMarkDTO.setUser_id(BaseContext.getCurrentId());
        if (gameMarkMapper.isMark(gameMarkDTO) != 1) {
            gameMarkMapper.mark(gameMarkDTO);
            gameMapper.updateScoreById(gameMarkDTO.getGame_id());
        }
    }

    @Override
    public PageVO<MyGameMarkVO> getMyGameMarkVOPage(PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        String user_id = BaseContext.getCurrentId();
        Page<MyGameMarkVO> myGameMarkVOPage = gameMarkMapper.getMyGameMarkVOPageLatest(user_id, new Page<>(current, size));
        return new PageVO<MyGameMarkVO>().objToVO(myGameMarkVOPage);
    }


}
