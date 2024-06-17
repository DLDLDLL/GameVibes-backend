package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.service.PostCollectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/post_collect")
public class PostCollectController {

    @Resource
    private PostCollectService postCollectService;

    @GetMapping("/list/page/vo")
    public BaseResponse<?> listPostCollectVOByPage(@RequestBody(required = false) PageRequest pageRequest) {
        return ResultUtils.success(postCollectService.getCollectPostVOPage(pageRequest));
    }

    @PostMapping("/collect")
    public BaseResponse<String> collect(@RequestParam("post_id") String post_id) {
        postCollectService.collect(post_id);
        return ResultUtils.success();
    }

    @PostMapping("/un_collect")
    public BaseResponse<String> unCollect(@RequestParam("post_id") String post_id) {
        postCollectService.unCollect(post_id);
        return ResultUtils.success();
    }
}
