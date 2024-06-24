package com.example.gamevibe.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * 分页请求
 *
 */
@Data
public class PageRequest {

    /**
     * 当前页号
     */
    @Min(value = 1, message = "当前页号最小为1")
    private int current = 1;

    /**
     * 页面大小
     */
    @Min(value = 1, message = "页面大小最小为1")
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = "ascend";
}
