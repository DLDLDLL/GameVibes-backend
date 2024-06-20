package com.example.gamevibe.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameMarkDTO implements Serializable {

    private String user_id;

    private Long game_id;

    private Double score;

    private String comment;

}
