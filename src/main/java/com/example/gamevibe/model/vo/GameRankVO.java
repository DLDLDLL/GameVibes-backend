package com.example.gamevibe.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GameRankVO implements Serializable {

    @ApiModelProperty(value = "游戏id", example = "1802728212422225921")
    private Long id;

    @ApiModelProperty(value = "游戏名称", example = "黑神话：悟空")
    private String name;

    @ApiModelProperty(value = "游戏图片", example = "https://cdn.casbin.org/img/casbin.svg")
    private List<String> image;

    @JsonIgnore
    @ApiModelProperty(value = "游戏类型", example = "类魂")
    private String type;

    @ApiModelProperty(value = "游戏评分", example = "9.9")
    private double score;


}