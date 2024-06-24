package com.example.gamevibe.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.LikeMessageMapper;
import com.example.gamevibe.model.entity.LikeMessage;
import com.example.gamevibe.model.vo.LikeMessageVO;
import com.example.gamevibe.service.LikeMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ZML
* @description 针对表【like_message(点赞消息表)】的数据库操作Service实现
* @createDate 2024-06-23 11:38:51
*/
@Service
public class LikeMessageServiceImpl extends ServiceImpl<LikeMessageMapper, LikeMessage> implements LikeMessageService {

    @Resource
    LikeMessageMapper likeMessageMapper;

    @Override
    public List<LikeMessageVO> listLikeMessage() {
        String user_id = BaseContext.getCurrentId();
        return likeMessageMapper.listLikeMessage(user_id);
    }


}




