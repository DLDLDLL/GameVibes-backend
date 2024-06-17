package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.PostCollectMapper;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.PostCollectVO;
import com.example.gamevibe.model.entity.PostCollect;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.PostCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author ZML
* @description 针对表【post_collect(帖子收藏表)】的数据库操作Service实现
* @createDate 2024-06-11 21:18:53
*/
@Service
public class PostCollectServiceImpl extends ServiceImpl<PostCollectMapper, PostCollect> implements PostCollectService {

    @Autowired
    private PostCollectMapper postCollectMapper;

    @Override
    public PageVO<List<PostCollectVO>, PostCollectVO> getCollectPostVOPage(PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        String user_id = BaseContext.getCurrentId();

        Page<PostCollectVO> collectPostPage = postCollectMapper.getCollectPostVOPage(user_id, new Page<>(current, size));

        return new PageVO<List<PostCollectVO>, PostCollectVO>().objToVO(collectPostPage);
    }

    @Override
    public void collect(String post_id) {
        String user_id = BaseContext.getCurrentId();
        postCollectMapper.saveCollect(user_id, post_id);
    }

    @Override
    public void unCollect(String post_id) {
        String user_id = BaseContext.getCurrentId();
        postCollectMapper.cancelCollect(user_id, post_id);
    }

}




