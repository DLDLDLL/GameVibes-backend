package com.example.gamevibe.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
@Data
@EqualsAndHashCode(callSuper = true)
public class GameQueryRequest extends PageRequest implements Serializable {

    private String searchText;

    @Serial
    private static final long serialVersionUID = 1L;
}
