package com.example.gamevibe.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * @TableName Game
 */
@TableName(value ="game")
@Data
public class Game implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    private String images;

    private String type;

    private BigDecimal score;

    private String intro;

    private Date create_time;

    private Date update_time;

    private Integer is_delete;

    @Serial
    private static final long serialVersionUID = 1L;
}