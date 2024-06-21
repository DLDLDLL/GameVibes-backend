package com.example.gamevibe.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MyPostVO implements Serializable {

    /**
     * 帖子id
     */
    @ApiModelProperty(value = "帖子id", example = "1")
    protected long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", example = "43ef5ba2-5b0a-43bc-a83e-fe3b3340c4ac")
    protected String user_id;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", example = "手机用户")
    protected String nick_name;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", example = "https://cdn.casbin.org/img/casbin.svg")
    protected String avatar;

    /**
     * 帖子发布时间
     */
    @ApiModelProperty(value = "帖子发布时间", example = "2024-06-18T11:00:42")
    protected LocalDateTime post_time;

    /**
     * 帖子标题
     */
    @ApiModelProperty(value = "帖子标题", example = "宝可梦对战入门")
    protected String title;

    /**
     * 帖子内容
     */
    @ApiModelProperty(value = "帖子内容", example = "帖子内容")
    protected String content;

    /**
     * 帖子图片
     */
    @ApiModelProperty(value = "帖子图片", example = "https://image.baidu.com/...")
    protected String images;

    /**
     * 帖子类型
     */
    @ApiModelProperty(value = "帖子类型", example = "宝可梦系列")
    protected String type;

    /**
     * 帖子评论数
     */
    @ApiModelProperty(value = "帖子评论数", example = "12")
    protected int comments;

    /**
     * 帖子点赞数
     */
    @ApiModelProperty(value = "帖子点赞数", example = "34")
    protected int like_count;

    /**
     * 是否点赞
     */
    @ApiModelProperty(value = "是否点赞", notes = "0为未点赞，1为已点赞", example = "1")
    protected int is_like;

    /**
     * 帖子收藏数
     */
    @ApiModelProperty(value = "帖子收藏数", example = "13")
    protected int collect_count;

    /**
     * 是否收藏
     */
    @ApiModelProperty(value = "是否收藏", notes = "0为未点赞，1为已点赞", example = "0")
    protected int is_collect;
}
