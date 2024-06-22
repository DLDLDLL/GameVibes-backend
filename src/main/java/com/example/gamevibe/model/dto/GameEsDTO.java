package com.example.gamevibe.model.dto;

import com.example.gamevibe.model.entity.Game;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.beans.BeanUtils;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serial;
import java.io.Serializable;

@Document(indexName = "game")
@Data
public class GameEsDTO implements Serializable {

    /**
     * id
     */
    @Id
    private Long id;

    private String name;

    private String type;

    private String intro;

    private Integer is_delete;

    @Serial
    private static final long serialVersionUID = 1L;

    public static GameEsDTO objToDTO(Game game) {
        if (game == null) return null;
        GameEsDTO gameEsDTO = new GameEsDTO();
        BeanUtils.copyProperties(game, gameEsDTO);
        return gameEsDTO;
    }

}
