package com.example.gamevibe.model.vo;

import cn.hutool.json.JSONUtil;
import com.example.gamevibe.model.dto.GameDetailsDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class GameDetailsVO extends GameDetailsDTO implements Serializable {

    private List<String> imageList;

    public GameDetailsVO DTOToVO(GameDetailsDTO gameDetailsDTO) {
        GameDetailsVO gameDetailsVO = new GameDetailsVO();
        BeanUtils.copyProperties(gameDetailsDTO, gameDetailsVO);
        gameDetailsVO.setImageList(JSONUtil.toList(gameDetailsDTO.getImages(), String.class));
        gameDetailsVO.getImageList().remove(0);
        return gameDetailsVO;
    }

}
