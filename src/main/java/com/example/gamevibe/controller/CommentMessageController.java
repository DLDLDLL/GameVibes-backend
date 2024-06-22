package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.CommentMessageVO;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.service.CommentMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(tags = "消息评论模块")
@RestController
@RequestMapping("/commentMessage")
public class CommentMessageController {
    @Resource
    CommentMessageService commentMessageService;

    /**
     * 评论消息查询
     *
     * @param pageRequest
     * @param request
     * @return
     */
    @ApiOperation(value = "查询评论消息")
    @PostMapping("/list/page")
    public BaseResponse<PageResult<CommentMessageVO>> listCommentMessageByPage(@RequestBody PageRequest pageRequest, HttpServletRequest request) throws IOException {
        return ResultUtils.success(commentMessageService.listCommentMessage(pageRequest));
    }
}
