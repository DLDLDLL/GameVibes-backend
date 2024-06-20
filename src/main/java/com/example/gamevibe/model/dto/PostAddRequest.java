package com.example.gamevibe.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostAddRequest implements Serializable {

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 标题
     */
    private String title;

    /**
     * 游戏类别
     */
    private String type;

    /**
     * 图片，多个图片以,隔开
     */
    private String images;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 帖子发布地点
     */
    private String location;

    private static final long serialVersionUID = 1L;

}
