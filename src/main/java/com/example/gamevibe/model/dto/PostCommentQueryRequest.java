package com.example.gamevibe.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serial;
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
    @NotNull(message = "帖子id不能为空")
    private long post_id;

    @Serial
    private static final long serialVersionUID = 1L;
}