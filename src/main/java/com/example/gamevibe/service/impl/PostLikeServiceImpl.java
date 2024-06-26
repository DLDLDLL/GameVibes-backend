package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.PostLikeMapper;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.MyPostLikeVO;
import com.example.gamevibe.model.entity.PostLike;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.PostLikeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * @author ZML
 * @description 针对表【post_price(帖子点赞表)】的数据库操作Service实现
 * @createDate 2024-06-11 21:18:53
 */
@Service
public class PostLikeServiceImpl extends ServiceImpl<PostLikeMapper, PostLike> implements PostLikeService {

    @Resource
    private PostLikeMapper postLikeMapper;

    @Override
    public PageVO<MyPostLikeVO> getLikePostVOPage(PageRequest pageRequest, String user_id) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        Page<MyPostLikeVO> likePostPage = postLikeMapper.getLikePostVOPage(user_id, new Page<>(current, size));
        return new PageVO<MyPostLikeVO>().objToVO(likePostPage);
    }

    @Override
    public void like(Long post_id) {
        String user_id = BaseContext.getCurrentId();
        postLikeMapper.saveLike(user_id, post_id);
        postLikeMapper.updateLikes(post_id, 1);
    }

    @Override
    public void unLike(Long post_id) {
        String user_id = BaseContext.getCurrentId();
        if (isLike(post_id)) {
            postLikeMapper.cancelLike(user_id, post_id);
            postLikeMapper.updateLikes(post_id, -1);
        }
    }

    @Override
    public boolean isLike(Long post_id) {
        String user_id = BaseContext.getCurrentId();
        return postLikeMapper.isLike(user_id, post_id) == 1;
    }


}




