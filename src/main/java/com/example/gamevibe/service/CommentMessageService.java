package com.example.gamevibe.service;

import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.CommentMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.vo.CommentMessageVO;
import com.example.gamevibe.model.vo.PageResult;

/**
* @author D
* @description 针对表【comment_message(评论消息表)】的数据库操作Service
* @createDate 2024-06-22 20:05:57
*/
public interface CommentMessageService extends IService<CommentMessage> {

    PageResult<CommentMessageVO> listCommentMessage(PageRequest pageRequest);
}
