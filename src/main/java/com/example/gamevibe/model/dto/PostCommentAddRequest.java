package com.example.gamevibe.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Data
public class PostCommentAddRequest implements Serializable {

    /**
     * 帖子id
     */
    @NotNull(message = "帖子id不能为空")
    private Long post_id;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空")
    private String content;

    /**
     * 评论地点
     */
    private String location;

    @Serial
    private static final long serialVersionUID = 1L;
}
