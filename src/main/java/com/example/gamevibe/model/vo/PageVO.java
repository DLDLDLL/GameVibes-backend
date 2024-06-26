package com.example.gamevibe.model.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Data
public class PageVO<T> implements Serializable {

    /**
     * 当前页
     */
    @JsonIgnore
    @ApiModelProperty(value = "当前页", example = "1")
    private long current;

    /**
     * 页大小
     */
    @JsonIgnore
    @ApiModelProperty(value = "页大小", example = "10")
    private long size;

    /**
     * 总数
     */
    @ApiModelProperty(value = "总数", example = "24")
    private long total;

    /**
     * 记录
     */
    @ApiModelProperty(value = "响应数据")
    private List<T> records;

    public PageVO<T> objToVO(Page<T> page) {
        PageVO<T> pageVO = new PageVO<>();
        BeanUtils.copyProperties(page, pageVO);
        return pageVO;
    }

}
