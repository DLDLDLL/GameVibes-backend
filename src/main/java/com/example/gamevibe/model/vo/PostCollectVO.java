package com.example.gamevibe.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class PostCollectVO extends PostLikeVO implements Serializable {

    /**
     * id           - 帖子id
     * user_id      - 用户id
     * nick_name    - 用户昵称
     * avatar       - 用户头像
     * post_time    - 帖子发布时间
     * title        - 帖子标题
     * content      - 帖子内容
     * images       - 帖子图片
     * comments     - 帖子评论数
     * like_count   - 帖子点赞数
     */

    /**
     * 用户是否点赞
     */
    private int is_like;

}
