package com.example.gamevibe.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
public class UserVO implements Serializable {

    @ApiModelProperty(value = "用户个人页展示ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户id(casdoor分配)", example = "43ef5ba2-5b0a-43bc-a83e-fe3b3340c4ac")
    private String user_id;

    @ApiModelProperty(value = "用户头像", example = "https://cdn.casbin.org/img/casbin.svg")
    private String avatar;

    @ApiModelProperty(value = "用户昵称", example = "手机用户")
    private String nick_name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "创建时间", example = "2024-06-18T11:00:42")
    private LocalDateTime create_time;

    @ApiModelProperty(value = "ip地址", example = "127.0.0.1")
    private String ip_addr;

    @ApiModelProperty(value = "用户简介", example = "专属于你的gamevibe!")
    private String intro;

    @ApiModelProperty(value = "关注数", example = "123")
    private int focus_count;

    @ApiModelProperty(value = "粉丝数", example = "321")
    private int fans_count;

    @ApiModelProperty(value = "点赞与收藏数", example = "1234")
    private int like_collect_count;

}
