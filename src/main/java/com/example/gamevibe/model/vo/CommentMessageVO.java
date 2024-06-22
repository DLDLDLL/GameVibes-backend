package com.example.gamevibe.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CommentMessageVO implements Serializable {

    /**
     * 用户头像
     */
    private String user_avatar;

    /**
     * 用户名称
     */
    private String user_name;

    /**
     * 被评论用户名
     */
    private String current_user_name;

    /**
     * 帖子标题
     */
    private String post_title;

    /**
     * 帖子图片
     */
    private List<String> image;

    /**
     * 评论内容
     */
    private String comment_content;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date comment_time;

    /**
     * 状态，0 未读，1 已读
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

}
