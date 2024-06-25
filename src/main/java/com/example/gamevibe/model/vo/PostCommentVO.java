package com.example.gamevibe.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class PostCommentVO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 帖子评论发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date post_time;

    private static final long serialVersionUID = 1L;
}
