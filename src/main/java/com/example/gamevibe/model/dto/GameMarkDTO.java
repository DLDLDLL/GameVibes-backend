package com.example.gamevibe.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel
public class GameMarkDTO implements Serializable {

    private String user_id;

    @NotNull(message = "游戏id不能为空")
    @ApiModelProperty(value = "游戏id", example = "1", required = true)
    private Long game_id;

    @Min(value = 1, message = "评分最低为1")
    @Max(value = 5, message = "评分最高为5")
    @ApiModelProperty(value = "评分", example = "3", required = true)
    private Integer score;

    @NotBlank(message = "评价不能为空")
    @ApiModelProperty(value = "评价", example = "...", required = true)
    private String comment;

}
