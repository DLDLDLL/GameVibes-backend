package com.example.gamevibe.model.vo;

import com.example.gamevibe.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * 首页推荐
 */
@Data
public class PostVO implements Serializable {

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
     * 帖子发布时间
     */
    private Date post_time;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 对象转包装类
     *
     * @param post
     * @return
     */
    public static PostVO objToVo(Post post) {
        if (post == null) {
            return null;
        }
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post, postVO);
        return postVO;
    }
}
