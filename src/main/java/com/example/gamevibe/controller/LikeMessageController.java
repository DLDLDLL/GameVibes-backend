package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.vo.LikeMessageVO;
import com.example.gamevibe.service.LikeMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "消息点赞模块")
@RestController
@RequestMapping("/like_message")
public class LikeMessageController {

    @Resource
    LikeMessageService likeMessageService;

    @ApiOperation(value = "查询点赞消息")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/list")
    public BaseResponse<List<LikeMessageVO>> listCollectMessage() {
        return ResultUtils.success(likeMessageService.listLikeMessage());
    }
}
