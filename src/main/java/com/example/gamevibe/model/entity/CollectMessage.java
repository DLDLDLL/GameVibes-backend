package com.example.gamevibe.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @TableName collect_message
 */
@TableName(value ="collect_message")
@Data
public class CollectMessage implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    protected Long id;

    protected String user_id;

    protected Long post_id;

    protected String post_title;

    protected String post_user_id;

    @JsonIgnore
    protected Integer status;

    protected Date create_time;

    @JsonIgnore
    protected Date update_time;

    @JsonIgnore
    protected Integer is_delete;

    @Serial
    private static final long serialVersionUID = 1L;

}