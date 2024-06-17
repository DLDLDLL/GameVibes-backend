package com.example.gamevibe.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserVO implements Serializable {

    private Long id;

    private String user_id;

    private String avatar;

    private String nick_name;

    private LocalDateTime create_time;

    private String ip_addr;

    private String intro;

    private int focus_count;

    private int fans_count;

    private int like_collect_count;

}
