package com.example.gamevibe.model.vo;

import com.example.gamevibe.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

@Data
public class PostHotVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 游戏类别
     */
    private String type;

    /**
     * 图片，多个图片以,隔开
     */
    private String images;

    /**
     * 评论数量
     */
    private Integer comments;

    /**
     * 帖子浏览量
     */
    private Double pv;

    /**
     * 对象转包装类
     *
     * @param post
     * @return
     */
    public static PostHotVO objToVo(Post post) {
        if (post == null) {
            return null;
        }
        PostHotVO postHotVO = new PostHotVO();
        BeanUtils.copyProperties(post, postHotVO);
        return postHotVO;
    }
}