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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
* @author ZML
* @description 针对表【post_price(帖子点赞表)】的数据库操作Service实现
* @createDate 2024-06-11 21:18:53
*/
@Service
public class PostLikeServiceImpl extends ServiceImpl<PostLikeMapper, PostLike> implements PostLikeService {

    @Autowired
    private PostLikeMapper postLikeMapper;

    @Override
    public PageVO<MyPostLikeVO> getLikePostVOPage(PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        String user_id = BaseContext.getCurrentId();

        Page<MyPostLikeVO> likePostPage = postLikeMapper.getLikePostVOPage(user_id, new Page<>(current, size));

        return new PageVO<MyPostLikeVO>().objToVO(likePostPage);
    }

    @Override
    public void like(String post_id) {
        String user_id = BaseContext.getCurrentId();
        postLikeMapper.saveLike(user_id, post_id);
    }

    @Override
    public void unLike(String post_id) {
        String user_id = BaseContext.getCurrentId();
        postLikeMapper.cancelLike(user_id, post_id);
    }


}




