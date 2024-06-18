package com.example.gamevibe.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 帖子评论分页查询请求 dto
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PostCommentQueryRequest extends PageRequest implements Serializable {

    /**
     * 帖子id
     */
    private long post_id;

    private static final long serialVersionUID = 1L;
}