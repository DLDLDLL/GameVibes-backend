package com.example.gamevibe.model.vo;

import com.example.gamevibe.model.entity.LikeMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class LikeMessageVO extends LikeMessage implements Serializable {

    /**
     * 昵称
     */
    private String post_user_name;

    /**
     * 头像
     */
    private String post_user_avatar;

    /**
     * 用户昵称(发起者)
     */
    private String user_name;

    /**
     * 用户头像(发起者)
     */
    private String user_avatar;

    @Serial
    private static final long serialVersionUID = 1L;

}
