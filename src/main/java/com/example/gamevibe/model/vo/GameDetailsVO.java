package com.example.gamevibe.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class GameDetailsVO implements Serializable {

    @ApiModelProperty(value = "游戏id", example = "1802728212422225921")
    private Long id;

    @ApiModelProperty(value = "游戏名称", example = "黑神话：悟空")
    private String name;

    @ApiModelProperty(value = "游戏图片", example = "https://cdn.casbin.org/img/casbin.svg,https://cdn.casbin.org/img/casbin.svg")
    private String images;

    @ApiModelProperty(value = "游戏简介", example = "...")
    private String intro;

    @ApiModelProperty(value = "游戏评分", example = "9.9")
    private Double score;

    @ApiModelProperty(value = "游戏类型", example = "类魂")
    private String type;

    @ApiModelProperty(value = "是否评分", example = "0", notes = "0为未评分, 1为已评分")
    private Integer is_mark;

}