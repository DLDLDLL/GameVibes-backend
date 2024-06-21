package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.GameDetailsVO;
import com.example.gamevibe.model.vo.GameRankVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.GameService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "游戏模块")
@RestController
@RequestMapping("/api/game")
public class GameController {

    @Resource
    private GameService gameService;

    @ApiOperation(value = "获取游戏排行榜", notes = "pageRequest默认为(current: 1, pageSize: 10)")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/list/page/vo")
    public BaseResponse<PageVO<GameRankVO>> listGameRankVOByPage(@RequestBody(required = false) PageRequest pageRequest) {
        return ResultUtils.success(gameService.getGameRankVOPage(pageRequest));
    }

    @ApiOperation(value = "获取游戏详情页")
    @ApiImplicitParam(name = "game_id", value = "游戏id", required = true, paramType = "query")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/details")
    public BaseResponse<GameDetailsVO> getGameDetails(@RequestParam Long game_id) {
        return ResultUtils.success(gameService.getGameDetailsVO(game_id));
    }


}
