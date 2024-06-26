package com.example.gamevibe.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.News;
import com.example.gamevibe.model.vo.NewsVO;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Api(tags = "资讯模块")
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
    @ApiOperation(value = "获取资讯")
    @PostMapping("/list/page")
    public BaseResponse<PageResult<NewsVO>> listNewsByPage(@RequestBody @Validated PageRequest pageRequest,
                                                           HttpServletRequest request) {
        return ResultUtils.success(newsService.getNewsPage(pageRequest));
    }
}
