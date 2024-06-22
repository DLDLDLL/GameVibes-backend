package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.model.dto.PostCommentAddRequest;
import com.example.gamevibe.model.dto.PostCommentQueryRequest;
import com.example.gamevibe.model.entity.CommentMessage;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.model.entity.PostComment;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.service.CommentMessageService;
import com.example.gamevibe.service.PostCommentService;
import com.example.gamevibe.mapper.PostCommentMapper;
import com.example.gamevibe.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author D
 * @description 针对表【post_comment(帖子评论表)】的数据库操作Service实现
 * @createDate 2024-06-09 09:55:39
 */
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment>
        implements PostCommentService {

    @Resource
    CommentMessageService commentMessageService;
    @Resource
    PostService postService;

    @Override
    public PageResult<PostComment> listCommentsByPage(PostCommentQueryRequest postCommentQueryRequest, HttpServletRequest request) {
        long postId = postCommentQueryRequest.getPost_id();
        int current = postCommentQueryRequest.getCurrent();
        int pageSize = postCommentQueryRequest.getPageSize();
        String sortOrder = postCommentQueryRequest.getSortOrder();
        String sortField = postCommentQueryRequest.getSortField();

        // 根据帖子id查询
        QueryWrapper<PostComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_id", postId);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals("ascend"), sortField);

        // 执行分页并封装
        Page<PostComment> page = page(new Page<>(current, pageSize), queryWrapper);
        PageResult<PostComment> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(page.getRecords());
        return pageResult;
    }

    @Override
    @Transactional
    public Long comment(PostCommentAddRequest postCommentAddRequest) {
        PostComment postComment = new PostComment();
        BeanUtils.copyProperties(postCommentAddRequest, postComment);

        String user_id = BaseContext.getCurrentId();
        postComment.setUser_id(user_id);

        // 保存帖子评论
        boolean saveComment = save(postComment);
        if (!saveComment) {
            log.error("发布帖子评论失败");
            return null;
        }

        // 保存评论消息
        Long postCommentId = postComment.getId();
        CommentMessage commentMessage = new CommentMessage();
        Long postId = postCommentAddRequest.getPost_id();
        Post post = postService.getById(postId);
        commentMessage.setUser_id(post.getUser_id());
        commentMessage.setPost_comment_id(postCommentId);
        boolean saveMessage = commentMessageService.save(commentMessage);
        if (!saveMessage) {
            log.error("保存帖子评论消息失败");
            return null;
        }
//        MessageVO messageVO = new MessageVO();
//        messageVO.setUser_id(user_id);
//        messageVO.setPost_id(postId);
//        messageVO.setPost_comment_id(postCommentId);
//        // 推送评论消息
//        SseServer.sendMessage(targetUserId, messageVO);
        return postCommentId;
    }
}




