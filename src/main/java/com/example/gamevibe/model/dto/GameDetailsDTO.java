package com.example.gamevibe.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GameDetailsDTO implements Serializable {

    @ApiModelProperty(value = "游戏id", example = "1802728212422225921")
    protected Long id;

    @ApiModelProperty(value = "游戏名称", example = "黑神话：悟空")
    protected String name;

    @JsonIgnore
    @ApiModelProperty(value = "游戏图片", example = "https://cdn.casbin.org/img/casbin.svg,https://cdn.casbin.org/img/casbin.svg")
    private String images;

    @ApiModelProperty(value = "游戏简介", example = "...")
    protected String intro;

    @JsonIgnore
    @ApiModelProperty(value = "游戏评分", example = "9.9")
    protected Double score;

    @JsonIgnore
    @ApiModelProperty(value = "游戏类型", example = "类魂")
    protected String type;

    @ApiModelProperty(value = "我的评分", example = "3", notes = "0为未评分或未登录, 否则为分数")
    protected Integer mark_score;


}