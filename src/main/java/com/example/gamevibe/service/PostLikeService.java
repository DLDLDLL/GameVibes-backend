package com.example.gamevibe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.MyPostLikeVO;
import com.example.gamevibe.model.entity.PostLike;
import com.example.gamevibe.model.vo.PageVO;


/**
 * @author ZML
 * @description 针对表【post_price(帖子点赞表)】的数据库操作Service
 * @createDate 2024-06-11 21:18:53
 */
public interface PostLikeService extends IService<PostLike> {

    PageVO<MyPostLikeVO> getLikePostVOPage(PageRequest pageRequest, String user_id);

    void like(Long post_id);

    void unLike(Long post_id);

    boolean isLike(Long post_id);
}
