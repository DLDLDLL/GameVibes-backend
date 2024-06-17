package com.example.gamevibe.model.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class FocusUserVO implements Serializable {

    /**
     * 用户id
     */
    private String user_id;

    /**
     * 用户昵称
     */
    private String nick_name;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户简介
     */
    private String intro;

}
