package com.example.gamevibe.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class GameMarkDTO implements Serializable {

    private String user_id;

    @ApiModelProperty(value = "游戏id", example = "1", required = true)
    private Long game_id;

    @ApiModelProperty(value = "评分", example = "3", required = true)
    private Integer score;

    @ApiModelProperty(value = "评价", example = "...", required = true)
    private String comment;

}
