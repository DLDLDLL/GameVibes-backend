package com.example.gamevibe.model.vo;

import cn.hutool.json.JSONUtil;
import com.example.gamevibe.model.entity.News;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 帖子展示
 */
@Data
public class NewsVO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String image;

    private static final long serialVersionUID = 1L;

    /**
     * 对象转包装类
     *
     * @param news
     * @return
     */

    public static NewsVO objToVo(News news) {
        if (news == null) {
            return null;
        }
        NewsVO newsVO = new NewsVO();
        BeanUtils.copyProperties(news, newsVO);
        List<String> list = JSONUtil.toList(news.getImages(), String.class);
        if (list != null && !list.isEmpty()) {
            newsVO.setImage(list.get(0));
        } else {
            newsVO.setImage("");
        }
        return newsVO;
    }
}