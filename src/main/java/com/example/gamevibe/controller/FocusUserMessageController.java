package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.vo.FocusUserMessageVO;
import com.example.gamevibe.service.FocusUserMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "消息关注模块")
@RestController
@RequestMapping("/focus_user_message")
public class FocusUserMessageController {

    @Resource
    FocusUserMessageService focusUserMessageService;

    @ApiOperation(value = "查询关注消息")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/list")
    public BaseResponse<List<FocusUserMessageVO>> listCollectMessage() {
        return ResultUtils.success(focusUserMessageService.listFocusUserMessage());
    }
}
