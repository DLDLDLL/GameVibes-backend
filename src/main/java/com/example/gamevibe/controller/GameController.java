package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ErrorCode;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.GameQueryRequest;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.GameDetailsVO;
import com.example.gamevibe.model.vo.GameRankVO;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.GameService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Slf4j
@Api(tags = "游戏模块")
@RestController
@RequestMapping("/api/game")
public class GameController {

    @Resource
    private GameService gameService;

    @ApiOperation(value = "获取游戏排行榜", notes = "pageRequest默认为(current: 1, pageSize: 10)")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/list/page/vo")
    public BaseResponse<PageVO<GameRankVO>> listGameRankVOByPage(@RequestBody(required = false) @Validated PageRequest pageRequest) {
        return ResultUtils.success(gameService.getGameRankVOPage(pageRequest));
    }

    @ApiOperation(value = "获取游戏详情页")
    @ApiImplicitParam(name = "game_id", value = "游戏id", required = true, paramType = "query")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/details")
    public BaseResponse<GameDetailsVO> getGameDetails(@RequestParam @NotNull(message = "游戏id") Long game_id) {
        return ResultUtils.success(gameService.getGameDetailsVO(game_id));
    }

    @ApiOperation(value = "搜素游戏")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/search")
    public BaseResponse<PageResult> searchGameVOByPage(@RequestBody @Validated GameQueryRequest gameQueryRequest, HttpServletRequest request) {
        PageResult pageResult = new PageResult();
        System.out.println(gameQueryRequest);
        try {
            pageResult = gameService.searchFromEs(gameQueryRequest);
        } catch (IOException e) {
            if (!(e.getMessage().contains("200 OK"))) {
                log.error("搜索错误", e);
                return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "搜索错误");
            }
        }
        return ResultUtils.success(pageResult);
    }


}
