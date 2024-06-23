package com.example.gamevibe.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PostAddRequest implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 游戏类别
     */
    private Long type;

    /**
     * 图片
     */
    private List<String> images;

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
