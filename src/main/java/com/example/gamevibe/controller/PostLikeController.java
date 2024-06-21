package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.MyPostLikeVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.PostLikeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "帖子点赞模块")
@RestController
@RequestMapping("/api/post_like")
public class PostLikeController {

    @Resource
    private PostLikeService postLikeService;

    @ApiOperation(value = "我的点赞", notes = "pageRequest默认为(current: 1, pageSize: 10)")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/list/page/vo")
    public BaseResponse<PageVO<MyPostLikeVO>> listPostLikeVOByPage(@RequestBody(required = false) PageRequest pageRequest) {
        return ResultUtils.success(postLikeService.getLikePostVOPage(pageRequest));
    }

    @ApiOperation(value = "点赞帖子")
    @ApiImplicitParam(name = "post_id", value = "帖子id", required = true, paramType = "query")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/like")
    public BaseResponse<String> like(@RequestParam("post_id") Long post_id) {
        postLikeService.like(post_id);
        return ResultUtils.success();
    }

    @ApiOperation(value = "取消点赞帖子")
    @ApiImplicitParam(name = "post_id", value = "帖子id", required = true, paramType = "query")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/un_like")
    public BaseResponse<String> unLike(@RequestParam("post_id") Long post_id) {
        postLikeService.unLike(post_id);
        return ResultUtils.success();
    }


}
