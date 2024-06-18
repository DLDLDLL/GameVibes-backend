package com.example.gamevibe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author D
* @description 针对表【news(官方资讯表)】的数据库操作Service
* @createDate 2024-06-12 13:46:35
*/
public interface NewsService extends IService<News> {
    Page<News> getNewsPage(PageRequest pageRequest);
}
