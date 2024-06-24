package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.PostCollectMapper;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.MyPostCollectVO;
import com.example.gamevibe.model.entity.PostCollect;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.PostCollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author ZML
 * @description 针对表【post_collect(帖子收藏表)】的数据库操作Service实现
 * @createDate 2024-06-11 21:18:53
 */
@Service
public class PostCollectServiceImpl extends ServiceImpl<PostCollectMapper, PostCollect> implements PostCollectService {

    @Resource
    private PostCollectMapper postCollectMapper;


    @Override
    public PageVO<MyPostCollectVO> getCollectPostVOPage(PageRequest pageRequest, String user_id) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        Page<MyPostCollectVO> collectPostPage = postCollectMapper.getCollectPostVOPage(user_id, new Page<>(current, size));
        return new PageVO<MyPostCollectVO>().objToVO(collectPostPage);
    }

    @Override
    public void collect(Long post_id) {
        String user_id = BaseContext.getCurrentId();
        postCollectMapper.saveCollect(user_id, post_id);
        postCollectMapper.updateFavours(post_id, 1);
    }

    @Override
    public void unCollect(Long post_id) {
        String user_id = BaseContext.getCurrentId();
        if (isCollect(post_id)) {
            postCollectMapper.cancelCollect(user_id, post_id);
            postCollectMapper.updateFavours(post_id, -1);
        }
    }

    @Override
    public boolean isCollect(Long post_id) {
        String user_id = BaseContext.getCurrentId();
        return postCollectMapper.isCollect(user_id, post_id) == 1;
    }

}




