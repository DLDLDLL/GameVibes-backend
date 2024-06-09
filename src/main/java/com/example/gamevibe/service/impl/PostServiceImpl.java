package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.model.vo.PostVO;
import com.example.gamevibe.service.PostService;
import com.example.gamevibe.mapper.PostMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author D
 * @description 针对表【post(帖子表)】的数据库操作Service实现
 * @createDate 2024-06-09 09:55:39
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService {

    @Override
    public Page<PostVO> getPostVOPage(PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        // 查询条件
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        String sortOrder = pageRequest.getSortOrder();
        String sortField = pageRequest.getSortField();
        queryWrapper.eq("is_delete", false);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals("ascend"),
                sortField);
        // 查询
        Page<Post> postPage = page(new Page<>(current, size), queryWrapper);
        List<Post> posts = postPage.getRecords();
        // 转换类型
        List<PostVO> postVOList = posts.stream().map(PostVO::objToVo).collect(Collectors.toList());
        Page<PostVO> postVOPage = new Page<>(current, size);
        postVOPage.setRecords(postVOList);

        return postVOPage;
    }
}




