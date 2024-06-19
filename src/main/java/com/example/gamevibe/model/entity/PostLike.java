package com.example.gamevibe.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName post_price
 */
@TableName(value ="post_like")
@Data
public class PostLike implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String user_id;

    private Long post_id;

    private Date create_time;

    private Date update_time;

    private Integer is_delete;

    @Serial
    private static final long serialVersionUID = 1L;
}