package com.example.gamevibe.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.PostHotVO;
import com.example.gamevibe.model.vo.PostVO;
import com.example.gamevibe.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {
    @Resource
    private PostService postService;

    /**
     * 分页获取 首页推荐列表
     *
     * @param pageRequest
     * @param request
     * @return
     */
    @PostMapping("/list/recommend")
    public BaseResponse<Page<PostVO>> listPostVOByPage(@RequestBody PageRequest pageRequest,
                                                       HttpServletRequest request) {
        return ResultUtils.success(postService.getPostVOPage(pageRequest));
    }

    /**
     * 分页获取 首页热门列表
     *
     * @param pageRequest
     * @param request
     * @return
     */
    @PostMapping("/list/hot")
    public BaseResponse<Page<PostHotVO>> listPostHotVOByPage(@RequestBody PageRequest pageRequest,
                                                          HttpServletRequest request) {
        return ResultUtils.success(postService.getPostHotVOPage(pageRequest));
    }
}
