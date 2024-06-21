package com.example.gamevibe.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class MyGameMarkVO extends GameMarkVO implements Serializable {

    @ApiModelProperty(value = "游戏id", example = "1802728212422225921")
    private Long game_id;

    @ApiModelProperty(value = "游戏名称", example = "黑神话：悟空")
    private String game_name;

    @ApiModelProperty(value = "游戏评分", example = "9.9")
    private Double game_score;

    @ApiModelProperty(value = "游戏图片", example = "https://cdn.casbin.org/img/casbin.svg")
    private String image;


}
