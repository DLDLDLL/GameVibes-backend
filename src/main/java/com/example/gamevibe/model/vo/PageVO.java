package com.example.gamevibe.model.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class PageVO<T, E> implements Serializable {

    /**
     * 当前页
     */
    private long current;

    /**
     * 页大小
     */
    private long size;

    /**
     * 总数
     */
    private long total;

    /**
     * 记录
     */
    private T records;

    public PageVO<T, E> objToVO(Page<E> page) {
        PageVO<T, E> pageVO = new PageVO<>();
        BeanUtils.copyProperties(page, pageVO);
        return pageVO;
    }

}
