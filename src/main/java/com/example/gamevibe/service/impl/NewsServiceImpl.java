package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.News;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.service.NewsService;
import com.example.gamevibe.mapper.NewsMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author D
* @description 针对表【news(官方资讯表)】的数据库操作Service实现
* @createDate 2024-06-12 13:46:35
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

    @Override
    public Page<News> getNewsPage(PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        // 查询条件
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        String sortField = pageRequest.getSortField();
        String sortOrder = pageRequest.getSortOrder();
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals("ascend"),sortField);

        return page(new Page<>(current, size), queryWrapper);
    }
}




