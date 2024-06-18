package com.example.gamevibe.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class FocusUserVO implements Serializable {

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id(casdoor分配)", example = "43ef5ba2-5b0a-43bc-a83e-fe3b3340c4ac")
    private String user_id;

    /**
     * 用户昵称
     */
    @ApiModelProperty(value = "用户昵称", example = "手机用户")
    private String nick_name;

    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像", example = "https://cdn.casbin.org/img/casbin.svg")
    private String avatar;

    /**
     * 用户简介
     */
    @ApiModelProperty(value = "用户简介", example = "专属于你的gamevibe!")
    private String intro;

    /**
     * 是否关注
     */
    @ApiModelProperty(value = "是否关注", notes = "0为未点赞，1为已点赞", example = "1")
    private int is_focus;

}
