package com.example.gamevibe.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FileVO implements Serializable {

    private List<String> filePaths;
}