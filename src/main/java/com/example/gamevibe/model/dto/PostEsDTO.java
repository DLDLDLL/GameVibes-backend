package com.example.gamevibe.model.dto;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.gamevibe.model.entity.Post;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子 ES 包装类
 *
 **/
@Document(indexName = "post")
@Data
public class PostEsDTO implements Serializable {

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 阅读量
     */
    private Long pv;

    /**
     * 创建时间
     */
    private Date post_time;

    /**
     * 是否删除
     */
    private Integer is_delete;

    private static final long serialVersionUID = 1L;

    /**
     * 对象转包装类
     *
     * @param post
     * @return
     */
    public static PostEsDTO objToDto(Post post) {
        if (post == null) {
            return null;
        }
        PostEsDTO postEsDTO = new PostEsDTO();
        BeanUtils.copyProperties(post, postEsDTO);
        return postEsDTO;
    }

}
