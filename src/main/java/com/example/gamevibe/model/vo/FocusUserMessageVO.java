package com.example.gamevibe.model.vo;

import com.example.gamevibe.model.entity.FocusUserMessage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class FocusUserMessageVO extends FocusUserMessage implements Serializable {

    private String user_name;

    private String user_avatar;

}
