package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.mapper.GameMarkMapper;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.GameMark;
import com.example.gamevibe.model.vo.GameMarkVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.GameMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ZML
 * @description 针对表【GameMark(游戏评分表)】的数据库操作Service实现
 * @createDate 2024-06-19 16:17:55
 */
@Service
public class GameMarkServiceImpl extends ServiceImpl<GameMarkMapper, GameMark> implements GameMarkService {

    @Autowired
    private GameMarkMapper gameMarkMapper;

    @Override
    public PageVO<GameMarkVO> getGameMarkVOPage(PageRequest pageRequest, Long game_id) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        Page<GameMarkVO> gameMarkVOPage = gameMarkMapper.getGameMarkVOPageLatest(game_id, new Page<>(current, size));
        return new PageVO<GameMarkVO>().objToVO(gameMarkVOPage);
    }

    //    @Override
    public void mark(Long game_id) {

    }

}
