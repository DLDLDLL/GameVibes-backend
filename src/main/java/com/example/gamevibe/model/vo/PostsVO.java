package com.example.gamevibe.model.vo;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.gamevibe.model.entity.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子展示
 */
@Data
public class PostsVO implements Serializable {

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
     * 图片
     */
    private String image;

    /**
     * 评论数量
     */
    private Integer comments;

    /**
     * 阅读量
     */
    private Long pv;

    /**
     * 帖子发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date post_time;

    private static final long serialVersionUID = 1L;

    /**
     * 对象转包装类
     *
     * @param post
     * @return
     */

    public static PostsVO objToVo(Post post) {
        if (post == null) {
            return null;
        }
        PostsVO postsVO = new PostsVO();
        BeanUtils.copyProperties(post, postsVO);
        List<String> list = JSONUtil.toList(post.getImages(), String.class);
        if (list != null && !list.isEmpty()) {
            postsVO.setImage(list.get(0));
        } else {
            postsVO.setImage("");
        }
        return postsVO;
    }
}

