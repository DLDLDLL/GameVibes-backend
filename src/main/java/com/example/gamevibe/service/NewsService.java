package com.example.gamevibe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.vo.NewsVO;
import com.example.gamevibe.model.vo.PageResult;

import javax.naming.ldap.PagedResultsControl;

/**
* @author D
* @description 针对表【news(官方资讯表)】的数据库操作Service
* @createDate 2024-06-12 13:46:35
*/
public interface NewsService extends IService<News> {
    PageResult<NewsVO> getNewsPage(PageRequest pageRequest);
}
