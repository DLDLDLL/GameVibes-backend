package com.example.gamevibe.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.entity.LikeMessage;
import com.example.gamevibe.model.vo.LikeMessageVO;

import java.util.List;

/**
* @author ZML
* @description 针对表【like_message(点赞消息表)】的数据库操作Service
* @createDate 2024-06-23 11:38:51
*/
public interface LikeMessageService extends IService<LikeMessage> {

    List<LikeMessageVO> listLikeMessage();
}
