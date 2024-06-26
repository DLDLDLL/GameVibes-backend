package com.example.gamevibe.service;

import com.example.gamevibe.model.dto.PostCommentAddRequest;
import com.example.gamevibe.model.dto.PostCommentQueryRequest;
import com.example.gamevibe.model.entity.PostComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.model.vo.PostCommentVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author D
 * @description 针对表【post_comment(帖子评论表)】的数据库操作Service
 * @createDate 2024-06-09 09:55:39
 */
public interface PostCommentService extends IService<PostComment> {

    PageResult<PostCommentVO> listCommentsByPage(PostCommentQueryRequest postCommentQueryRequest, HttpServletRequest request);

    Long comment(PostCommentAddRequest postCommentAddRequest);
}
