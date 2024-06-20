package com.example.gamevibe.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostCommentAddRequest implements Serializable {

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 帖子id
     */
    private Long post_id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论地点
     */
    private String location;

    private static final long serialVersionUID = 1L;
}
