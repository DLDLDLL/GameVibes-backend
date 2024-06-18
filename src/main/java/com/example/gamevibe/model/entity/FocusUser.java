package com.example.gamevibe.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName focus_user
 */
@TableName(value ="focus_user")
@Data
public class FocusUser implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long user_id;

    private Long focused_id;

    private Date create_time;

    private Date update_time;

    private Integer state;

    @Serial
    private static final long serialVersionUID = 1L;
}