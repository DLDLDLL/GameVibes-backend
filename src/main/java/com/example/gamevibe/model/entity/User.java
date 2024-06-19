package com.example.gamevibe.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String user_id;

    private String nick_name;

    private String intro;

    private String ip_addr;

    private Date create_time;

    private Date update_time;

    private Integer is_delete;

    @Serial
    private static final long serialVersionUID = 1L;
}