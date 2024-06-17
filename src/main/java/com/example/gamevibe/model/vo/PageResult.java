package com.example.gamevibe.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult implements Serializable {
    private long total;

    private List records;
}
