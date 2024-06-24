package com.example.gamevibe.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.UserMapper;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.CommentMessage;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.model.entity.PostComment;
import com.example.gamevibe.model.vo.CommentMessageVO;
import com.example.gamevibe.model.vo.PageResult;
import com.example.gamevibe.model.vo.UserVO;
import com.example.gamevibe.service.CommentMessageService;
import com.example.gamevibe.mapper.CommentMessageMapper;
import com.example.gamevibe.service.PostCommentService;
import com.example.gamevibe.service.PostService;
import com.example.gamevibe.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author D
 * @description 针对表【comment_message(评论消息表)】的数据库操作Service实现
 * @createDate 2024-06-22 20:05:57
 */
@Service
public class CommentMessageServiceImpl extends ServiceImpl<CommentMessageMapper, CommentMessage>
        implements CommentMessageService {
    @Resource
    PostCommentService postCommentService;
    @Resource
    UserMapper userMapper;
    @Resource
    PostService postService;

    @Override
    public PageResult<CommentMessageVO> listCommentMessage(PageRequest pageRequest) {

        int current = pageRequest.getCurrent();
        int pageSize = pageRequest.getPageSize();
        String sortOrder = pageRequest.getSortOrder();
        String sortField = pageRequest.getSortField();
        String user_id = BaseContext.getCurrentId();

        // 根据 被评论用户id 查询评论消息
        QueryWrapper<CommentMessage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user_id);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField), sortOrder.equals("ascend"), sortField);
        Page<CommentMessage> page = page(new Page<>(current, pageSize), queryWrapper);
        List<CommentMessage> commentMessageList = page.getRecords();

        // 封装
        List<CommentMessageVO> commentMessageVOList = new ArrayList<>();
        for (CommentMessage commentMessage : commentMessageList) {
            CommentMessageVO commentMessageVO = new CommentMessageVO();
            commentMessageVO.setCurrent_user_name(BaseContext.getCurrentName());
            commentMessageVO.setStatus(commentMessage.getStatus());
            // 帖子评论
            Long postComment__id = commentMessage.getPost_comment_id();
            PostComment postComment = postCommentService.getById(postComment__id);
            commentMessageVO.setComment_content(postComment.getContent());
            commentMessageVO.setComment_time(postComment.getPost_name());
            // 评论用户
            String userId = postComment.getUser_id();
            UserVO userInfo = userMapper.getUserInfo(userId);
            commentMessageVO.setUser_name(userInfo.getNick_name());
            commentMessageVO.setUser_avatar(userInfo.getAvatar());
            // 帖子
            Long postId = postComment.getPost_id();
            Post post = postService.getById(postId);
            List<String> images = JSONUtil.toList(post.getImages(), String.class);
            commentMessageVO.setPost_title(post.getTitle());
            commentMessageVO.setImage(images);

            commentMessageVOList.add(commentMessageVO);

            //更新状态为已读
            if (commentMessage.getStatus() == 0) {
                commentMessage.setStatus(1);
                updateById(commentMessage);
            }
        }
        PageResult<CommentMessageVO> pageResult = new PageResult<>();
        pageResult.setTotal(page.getTotal());
        pageResult.setRecords(commentMessageVOList);

        return pageResult;
    }
}




