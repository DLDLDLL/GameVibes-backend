package com.example.gamevibe.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MyPostVO implements Serializable {

    /**
     * 帖子id
     */
    protected long id;

    /**
     * 用户id
     */
    protected String user_id;

    /**
     * 用户昵称
     */
    protected String nick_name;

    /**
     * 用户头像
     */
    protected String avatar;

    /**
     * 帖子发布时间
     */
    protected LocalDateTime post_time;

    /**
     * 帖子标题
     */
    protected String title;

    /**
     * 帖子内容
     */
    protected String content;

    /**
     * 帖子图片
     */
    protected String images;

    /**
     * 帖子评论数
     */
    protected int comments;

    /**
     * 帖子点赞数
     */
    protected int like_count;

    /**
     * 是否点赞
     */
    protected int is_like;

    /**
     * 帖子收藏数
     */
    protected int collect_count;

    /**
     * 是否收藏
     */
    protected int is_collect;
}
