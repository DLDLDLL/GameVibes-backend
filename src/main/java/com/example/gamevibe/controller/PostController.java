package com.example.gamevibe.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ErrorCode;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.dto.PostAddRequest;
import com.example.gamevibe.model.dto.PostQueryRequest;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.example.gamevibe.model.vo.PostVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Api(tags = "帖子模块")
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
    @ApiOperation(value = "获取首页推荐列表")
    @PostMapping("/list/recommend")
    public BaseResponse<PageResult<PostVO>> listPostVOByPage(@RequestBody PageRequest pageRequest,
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
    @ApiOperation(value = "获取首页热门列表")
    @PostMapping("/list/hot")
    public BaseResponse<PageResult<PostVO>> listPostVOHotByPage(@RequestBody PageRequest pageRequest,
                                                   HttpServletRequest request) {
        return ResultUtils.success(postService.getPostPage(pageRequest));
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "查询帖子")
    @GetMapping("/get")
    public BaseResponse<PostVO> getPostVOById(@RequestParam long id, HttpServletRequest request) {
        return ResultUtils.success(postService.getPostById(id, request));
    }

    /**
     * 分页搜索（从 ES 查询）
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
    @ApiOperation(value = "搜索帖子")
    @PostMapping("/search")
    public BaseResponse<PageResult> searchPostVOByPage(@RequestBody PostQueryRequest postQueryRequest,
                                                       HttpServletRequest request) {
        PageResult pageResult=new PageResult();
        try{
            pageResult = postService.searchFromEs(postQueryRequest);
        }catch (IOException e){
            if(!(e.getMessage().contains("200 OK"))){
                log.error("搜索错误",e);
                return ResultUtils.error(ErrorCode.SYSTEM_ERROR,"搜索错误");
            }
        }
        return ResultUtils.success(pageResult);
    }

    @ApiOperation(value = "发布帖子")
    @PostMapping("/add")
    public BaseResponse<Long> addPost(@RequestBody PostAddRequest postAddRequest,
                                            HttpServletRequest request) {
        return ResultUtils.success(postService.addPost(postAddRequest));
    }

}
