package com.example.gamevibe.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class PostAddRequest implements Serializable {

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空")
    private String title;

    /**
     * 游戏类别
     */
    private String type;

    /**
     * 图片
     */
    @NotEmpty(message = "图片不能为空")
    private List<String> images;

    /**
     * 帖子内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 帖子发布地点
     */
    private String location;

    @Serial
    private static final long serialVersionUID = 1L;

}
