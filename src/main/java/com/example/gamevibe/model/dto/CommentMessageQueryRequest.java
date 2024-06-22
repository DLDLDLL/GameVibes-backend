package com.example.gamevibe.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentMessageQueryRequest extends PageRequest implements Serializable {
    /**
     * 用户id
     */
    String user_id;

    private static final long serialVersionUID = 1L;


}
