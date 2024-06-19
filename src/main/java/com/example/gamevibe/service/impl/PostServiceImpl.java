package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.dto.PostEsDTO;
import com.example.gamevibe.model.dto.PostQueryRequest;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.service.PostService;
import com.example.gamevibe.mapper.PostMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author D
 * @description 针对表【post(帖子表)】的数据库操作Service实现
 * @createDate 2024-06-09 09:55:39
 */
@Service
@Slf4j
public class PostServiceImpl extends ServiceImpl<PostMapper, com.example.gamevibe.model.entity.Post>
        implements PostService {
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    public Page<Post> getPostPage(PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        // 查询条件
        QueryWrapper<com.example.gamevibe.model.entity.Post> queryWrapper = new QueryWrapper<>();
        String sortOrder = pageRequest.getSortOrder();
        String sortField = pageRequest.getSortField();
        queryWrapper.eq("is_delete", 0);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals("ascend"), sortField);

        // 查询
        return page(new Page<>(current, size), queryWrapper);
    }

    @Override
    public Post getPostById(long id, HttpServletRequest request) {
        // 阅读量+1
        Post post = getById(id);
        if (post == null) {
            return null;
        }
        post.setPv(post.getPv() + 1);
        updateById(post);
        // TODO 展示评论，查询点赞，查询收藏
        return post;
    }

    @Override
    public PageResult searchFromEs(PostQueryRequest postQueryRequest) throws IOException {
        String searchText = postQueryRequest.getSearchText();
        // es 起始页为 0
        long current = postQueryRequest.getCurrent() - 1;
        long pageSize = postQueryRequest.getPageSize();
        String sortField = postQueryRequest.getSortField();
        String sortOrder = postQueryRequest.getSortOrder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 过滤
        boolQueryBuilder.filter(QueryBuilders.termQuery("is_delete", 0));
        // 按关键词检索
        if (StringUtils.isNotBlank(searchText)) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("title", searchText));
            boolQueryBuilder.should(QueryBuilders.matchQuery("content", searchText));
            boolQueryBuilder.minimumShouldMatch(1);
        }
        // 排序
        SortBuilder<?> sortBuilder = SortBuilders.scoreSort();
        if (StringUtils.isNotBlank(sortField)) {
            sortBuilder = SortBuilders.fieldSort(sortField);
            sortBuilder.order("ascend".equals(sortOrder) ? SortOrder.ASC : SortOrder.DESC);
        }
        // 分页
        org.springframework.data.domain.PageRequest pageRequest = org.springframework.data.domain.PageRequest.of((int) current, (int) pageSize);
        // 构造查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder)
                .withPageable(pageRequest).withSorts(sortBuilder).build();
        SearchHits<PostEsDTO> searchHits = elasticsearchRestTemplate.search(searchQuery, PostEsDTO.class);
        PageResult page = new PageResult();
        page.setTotal(searchHits.getTotalHits());
        List<Post> postList = new ArrayList<>();
        // 结果
        if (searchHits.hasSearchHits()) {
            List<SearchHit<PostEsDTO>> searchHitList = searchHits.getSearchHits();
            List<Long> postIdList = searchHitList.stream().map(searchHit -> searchHit.getContent().getId())
                    .collect(Collectors.toList());
            postList = listByIds(postIdList);
        }
        page.setRecords(postList);
        return page;
    }

}




