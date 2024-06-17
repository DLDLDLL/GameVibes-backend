package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.service.PostLikeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/post_like")
public class PostLikeController {

    @Resource
    private PostLikeService postLikeService;

    @GetMapping("/list/page/vo")
    public BaseResponse<?> listPostLikeVOByPage(@RequestBody(required = false) PageRequest pageRequest) {
        return ResultUtils.success(postLikeService.getLikePostVOPage(pageRequest));
    }

    @PostMapping("/like")
    public BaseResponse<String> like(@RequestParam("post_id") String post_id) {
        postLikeService.like(post_id);
        return ResultUtils.success();
    }

    @PostMapping("/un_like")
    public BaseResponse<String> unLike(@RequestParam("post_id") String post_id) {
        postLikeService.unLike(post_id);
        return ResultUtils.success();
    }


}
