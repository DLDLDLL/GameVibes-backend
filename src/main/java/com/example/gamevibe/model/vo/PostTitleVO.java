package com.example.gamevibe.model.vo;

import cn.hutool.json.JSONUtil;
import com.example.gamevibe.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Data
public class PostTitleVO implements Serializable {

    /**
     * 标题
     */
    private String title;

    private static final long serialVersionUID = 1L;

    /**
     * 对象转包装类
     *
     * @param post
     * @return
     */
    public static PostTitleVO objToVo(Post post) {
        if (post == null) {
            return null;
        }
        PostTitleVO postTitleVO = new PostTitleVO();
        BeanUtils.copyProperties(post, postTitleVO);
        return postTitleVO;
    }
}