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
 * @TableName focus_user_message
 */
@TableName(value ="focus_user_message")
@Data
public class FocusUserMessage implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    protected Long id;

    protected String user_id;

    @JsonIgnore
    protected Long focus_user_id;

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