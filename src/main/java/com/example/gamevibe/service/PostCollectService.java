package com.example.gamevibe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.MyPostCollectVO;
import com.example.gamevibe.model.entity.PostCollect;
import com.example.gamevibe.model.vo.PageVO;

/**
* @author ZML
* @description 针对表【post_collect(帖子收藏表)】的数据库操作Service
* @createDate 2024-06-11 21:18:53
*/
public interface PostCollectService extends IService<PostCollect> {

    PageVO<MyPostCollectVO> getCollectPostVOPage(PageRequest pageRequest);

    void collect(Long post_id);

    void unCollect(Long post_id);

    boolean isCollect(Long post_id);
}
