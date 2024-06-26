package com.example.gamevibe.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private long total;

    private List<T> records;
}
