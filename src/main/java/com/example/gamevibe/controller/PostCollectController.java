package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.MyPostCollectVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.PostCollectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "帖子收藏模块")
@RestController
@RequestMapping("/api/post_collect")
public class PostCollectController {

    @Resource
    private PostCollectService postCollectService;

    @ApiOperation(value = "收藏列表", notes = "pageRequest默认为(current: 1, pageSize: 10)")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/list/page/vo")
    public BaseResponse<PageVO<MyPostCollectVO>> listPostCollectVOByPage(@RequestBody(required = false) PageRequest pageRequest, @RequestParam(required = false) String user_id) {
        return ResultUtils.success(postCollectService.getCollectPostVOPage(pageRequest, user_id));
    }

    @ApiOperation(value = "收藏帖子")
    @ApiImplicitParam(name = "post_id", value = "帖子id", required = true, paramType = "query")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/collect")
    public BaseResponse<String> collect(@RequestParam("post_id") Long post_id) {
        postCollectService.collect(post_id);
        return ResultUtils.success();
    }

    @ApiOperation(value = "取消收藏帖子")
    @ApiImplicitParam(name = "post_id", value = "帖子id", required = true, paramType = "query")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/un_collect")
    public BaseResponse<String> unCollect(@RequestParam("post_id") Long post_id) {
        postCollectService.unCollect(post_id);
        return ResultUtils.success();
    }
}
