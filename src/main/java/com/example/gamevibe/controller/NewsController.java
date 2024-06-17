package com.example.gamevibe.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.News;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.service.NewsService;
import com.example.gamevibe.service.PostService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/news")
public class NewsController {
    @Resource
    private NewsService newsService;

    /**
     * 分页获取 首页资讯列表
     *
     * @param pageRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<News>> listNewsByPage(@RequestBody PageRequest pageRequest,
                                                     HttpServletRequest request) {
        return ResultUtils.success(newsService.getNewsPage(pageRequest));
    }
}
