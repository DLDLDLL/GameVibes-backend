package com.example.gamevibe.service;

import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.GameMark;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.vo.GameMarkVO;
import com.example.gamevibe.model.vo.PageVO;

/**
 * @author ZML
 * @description 针对表【GameMark(游戏评分表)】的数据库操作Service
 * @createDate 2024-06-19 16:17:55
 */
public interface GameMarkService extends IService<GameMark> {

    PageVO<GameMarkVO> getGameMarkVOPage(PageRequest pageRequest, Long game_id);
}

