package com.example.gamevibe.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.dto.PostQueryRequest;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.model.vo.PageResult;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author D
 * @description 针对表【post(帖子表)】的数据库操作Service
 * @createDate 2024-06-09 09:55:39
 */
public interface PostService extends IService<Post> {


    Page<Post> getPostPage(PageRequest pageRequest);

    Post getPostById(long id, HttpServletRequest request);

    PageResult searchFromEs(PostQueryRequest postQueryRequest)throws IOException;

}
