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
public class PostVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String user_name;

    /**
     * 用户头像
     */
    private String avatar;

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
    private List<String> images;

    /**
     * 帖子内容
     */
    private String content;

    /**
     * 帖子发布地点
     */
    private String location;

    /**
     * 评论数量
     */
    private Integer comments;

    /**
     * 点赞数量
     */
    private Integer likes;

    /**
     * 收藏数量
     */
    private Integer favours;

    /**
     * 阅读量
     */
    private Long pv;

    /**
     * 是否点赞
     */
    private Integer is_like;

    private Integer is_favor;

    private Integer is_focus;

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
    public static PostVO objToVo(Post post) {
        if (post == null) {
            return null;
        }
        PostVO postVO = new PostVO();
        BeanUtils.copyProperties(post, postVO);
        postVO.setImages(JSONUtil.toList(post.getImages(),String.class));
        return postVO;
    }
}
