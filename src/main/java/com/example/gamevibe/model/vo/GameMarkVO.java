package com.example.gamevibe.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class GameMarkVO implements Serializable {

    @ApiModelProperty(value = "用户id", example = "43ef5ba2-5b0a-43bc-a83e-fe3b3340c4ac")
    protected String user_id;

    @ApiModelProperty(value = "用户头像", example = "https://cdn.casbin.org/img/casbin.svg")
    protected String user_avatar;

    @ApiModelProperty(value = "用户昵称", example = "手机用户")
    protected String user_name;

    @ApiModelProperty(value = "发布时间", example = "2024-06-18T11:00:42")
    protected LocalDateTime create_time;

    @ApiModelProperty(value = "评分", example = "3")
    protected Integer score;

    @ApiModelProperty(value = "评价", example = "...")
    protected String comment;


}
