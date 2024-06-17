package com.example.gamevibe.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.dto.PostQueryRequest;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public BaseResponse<Page<Post>> listPostVOByPage(@RequestBody PageRequest pageRequest,
                                                     HttpServletRequest request) {
        return ResultUtils.success(postService.getPostPage(pageRequest));
    }

    /**
     * 分页获取 首页热门列表
     *
     * @param pageRequest
     * @param request
     * @return
     */
    @PostMapping("/list/hot")
    public BaseResponse<Page<Post>> listPostVOHotByPage(@RequestBody PageRequest pageRequest,
                                                   HttpServletRequest request) {
        return ResultUtils.success(postService.getPostPage(pageRequest));
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<Post> getPostVOById(@RequestParam long id, HttpServletRequest request) {
        return ResultUtils.success(postService.getPostById(id, request));
    }

    /**
     * 分页搜索（从 ES 查询）
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/search")
    public BaseResponse<PageResult> searchPostVOByPage(@RequestBody PostQueryRequest postQueryRequest,
                                                       HttpServletRequest request) {
        return ResultUtils.success(postService.searchFromEs(postQueryRequest));
    }

}
