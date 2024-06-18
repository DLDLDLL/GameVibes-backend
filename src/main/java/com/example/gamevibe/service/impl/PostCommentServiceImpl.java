package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.model.dto.PostCommentQueryRequest;
import com.example.gamevibe.model.entity.PostComment;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.service.PostCommentService;
import com.example.gamevibe.mapper.PostCommentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author D
 * @description 针对表【post_comment(帖子评论表)】的数据库操作Service实现
 * @createDate 2024-06-09 09:55:39
 */
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment>
        implements PostCommentService {

    @Override
    public PageResult listCommentsByPage(PostCommentQueryRequest postCommentQueryRequest, HttpServletRequest request) {
        long postId = postCommentQueryRequest.getPost_id();
        int current = postCommentQueryRequest.getCurrent();
        int pageSize = postCommentQueryRequest.getPageSize();
        String sortOrder = postCommentQueryRequest.getSortOrder();
        String sortField = postCommentQueryRequest.getSortField();

        // 根据帖子id查询
        QueryWrapper<PostComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals("ascend"), sortField);

        // 执行分页并封装
        Page<PostComment> page = page(new Page<>(current, pageSize), queryWrapper);
        PageResult pageResult = new PageResult();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getRecords());
        return pageResult;
    }
}




