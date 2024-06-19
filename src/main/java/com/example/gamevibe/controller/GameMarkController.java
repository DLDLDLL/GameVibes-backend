package com.example.gamevibe.controller;


import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.GameMarkVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.GameMarkService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "游戏评分模块")
@RestController
@RequestMapping("/api/game_mark")
public class GameMarkController {

    @Resource
    private GameMarkService gameMarkService;

    @ApiOperation(value = "获取游戏详情页点评", notes = "pageRequest默认为(current: 1, pageSize: 10)")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "pageRequest", value = "分页参数", paramType = "body"),
            @ApiImplicitParam(name = "game_id", value = "游戏id", paramType = "query")
    })
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/list/page/vo")
    public BaseResponse<PageVO<GameMarkVO>> listGameMarkVOByPage(@RequestBody(required = false) PageRequest pageRequest, @RequestParam Long game_id) {
        return ResultUtils.success(gameMarkService.getGameMarkVOPage(pageRequest, game_id));
    }



}
