package com.example.gamevibe.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serial;
import java.io.Serializable;

/**
 * 分页请求
 *
 */
@Data
public class PageRequest implements Serializable {

    /**
     * 当前页号
     */
    @Min(value = 1, message = "当前页号最小为1")
    protected int current = 1;

    /**
     * 页面大小
     */
    @Min(value = 1, message = "页面大小最小为1")
    protected int pageSize = 10;

    /**
     * 排序字段
     */
    protected String sortField;

    /**
     * 排序顺序（默认升序）
     */
    protected String sortOrder = "ascend";

    @Serial
    private static final long serialVersionUID = 1L;

}
