package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.vo.CollectMessageVO;
import com.example.gamevibe.service.CollectMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "消息收藏模块")
@RestController
@RequestMapping("/collect_message")
public class CollectMessageController {

    @Resource
    CollectMessageService collectMessageService;

    @ApiOperation(value = "查询收藏消息")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/list")
    public BaseResponse<List<CollectMessageVO>> listCollectMessage() {
        return ResultUtils.success(collectMessageService.listCollectMessage());
    }



}
