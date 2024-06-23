package com.example.gamevibe.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 帖子评论表
 * @TableName post_comment
 */
@TableName(value ="post_comment")
@Data
public class PostComment implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

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

    /**
     * 评论发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date post_name;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date create_time;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date update_time;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer is_delete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}