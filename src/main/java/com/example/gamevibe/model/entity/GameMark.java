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
 * @TableName GameMark
 */
@TableName(value ="game_mark")
@Data
public class GameMark implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private String user_id;

    private Long game_id;

    private BigDecimal score;

    private String comment;

    private Date create_time;

    private Date update_time;

    private Integer is_delete;

    @Serial
    private static final long serialVersionUID = 1L;
}
