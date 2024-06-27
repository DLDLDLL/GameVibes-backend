package com.example.gamevibe.controller;


import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.GameMarkDTO;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.GameMarkVO;
import com.example.gamevibe.model.vo.MyGameMarkVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.GameMarkService;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Api(tags = "游戏评分模块")
@RestController
@RequestMapping("/api/game_mark")
public class GameMarkController {

    @Resource
    private GameMarkService gameMarkService;

    @ApiOperation(value = "获取游戏详情页点评", notes = "pageRequest默认为(current: 1, pageSize: 10)")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/list/page/vo")
    public BaseResponse<PageVO<GameMarkVO>> listGameMarkVOByPage(@RequestBody(required = false) @Validated PageRequest pageRequest, @RequestParam @NotNull(message = "游戏id不能为空") Long game_id) {
        return ResultUtils.success(gameMarkService.getGameMarkVOPage(pageRequest, game_id));
    }

    @ApiOperation(value = "点评")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/mark")
    public BaseResponse<String> mark(@RequestBody @Validated GameMarkDTO gameMarkDTO) {
        gameMarkService.mark(gameMarkDTO);
        return ResultUtils.success();
    }

    @ApiOperation(value = "我的点评")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/me/list/page/vo")
    public BaseResponse<PageVO<MyGameMarkVO>> listMyGameMarkVOByPage(@RequestBody(required = false) @Validated PageRequest pageRequest) {
        return ResultUtils.success(gameMarkService.getMyGameMarkVOPage(pageRequest));
    }



}
