package com.example.gamevibe.model.vo;

import com.example.gamevibe.model.entity.Game;
import com.example.gamevibe.model.entity.Post;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class GameNameVO implements Serializable {

    /**
     * id
     */
    @ApiModelProperty(value = "游戏id", example = "1802728212422225921")
    private Long id;

    /**
     * 名
     */
    @ApiModelProperty(value = "游戏名称", example = "黑神话：悟空")
    private String name;

    private static final long serialVersionUID = 1L;

    /**
     * 对象转包装类
     *
     * @param game
     * @return
     */
    public static GameNameVO objToVo(Game game) {
        if (game == null) {
            return null;
        }
        GameNameVO gameNameVO = new GameNameVO();
        BeanUtils.copyProperties(game, gameNameVO);
        return gameNameVO;
    }
}