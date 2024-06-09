package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.model.entity.PostComment;
import com.example.gamevibe.service.PostCommentService;
import com.example.gamevibe.mapper.PostCommentMapper;
import org.springframework.stereotype.Service;

/**
* @author D
* @description 针对表【post_comment(帖子评论表)】的数据库操作Service实现
* @createDate 2024-06-09 09:55:39
*/
@Service
public class PostCommentServiceImpl extends ServiceImpl<PostCommentMapper, PostComment>
    implements PostCommentService{

}




