package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.model.vo.PostHotVO;
import com.example.gamevibe.model.vo.PostVO;
import com.example.gamevibe.service.PostService;
import com.example.gamevibe.mapper.PostMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.gamevibe.common.Constants.POST_PV_KEY;

/**
 * @author D
 * @description 针对表【post(帖子表)】的数据库操作Service实现
 * @createDate 2024-06-09 09:55:39
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Page<PostVO> getPostVOPage(PageRequest pageRequest) {
        // 查询
        Page<Post> postPage = getPostPage(pageRequest);
        List<Post> posts = postPage.getRecords();
        // 转换类型
        List<PostVO> postVOList = posts.stream().map(PostVO::objToVo).collect(Collectors.toList());
        Page<PostVO> postVOPage = new Page<>(pageRequest.getCurrent(), pageRequest.getPageSize());
        postVOPage.setRecords(postVOList);

        return postVOPage;
    }

    @Override
    public Page<PostHotVO> getPostHotVOPage(PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long pageSize = pageRequest.getPageSize();
        Page<PostHotVO> postHotVOPage = new Page<>(current, pageSize);
        // 查询pv排行
        long start=pageSize*(current-1);
        long end=start+pageSize;
        Set<ZSetOperations.TypedTuple<String>> pvRanks = stringRedisTemplate.opsForZSet().reverseRangeWithScores(POST_PV_KEY, start, end);
        // 封装
        List<PostHotVO> postHotVOList = new ArrayList<>();
        for (ZSetOperations.TypedTuple<String> rank : pvRanks) {
            Long postId = Long.valueOf(rank.getValue());
            Double count = rank.getScore();

            Post post = getById(postId);
            PostHotVO postHotVO = new PostHotVO();
            BeanUtils.copyProperties(post, postHotVO);
            postHotVO.setPv(count);
            postHotVOList.add(postHotVO);
        }
        postHotVOPage.setRecords(postHotVOList);
        return postHotVOPage;
    }

    private Page<Post> getPostPage(PageRequest pageRequest) {
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
        return page(new Page<>(current, size), queryWrapper);
    }
}




