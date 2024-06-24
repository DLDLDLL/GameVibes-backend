package com.example.gamevibe.strategy.impl;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.LikeMessageMapper;
import com.example.gamevibe.mapper.PostLikeMapper;
import com.example.gamevibe.model.entity.LikeMessage;
import com.example.gamevibe.model.vo.LikeMessageVO;
import com.example.gamevibe.strategy.MessageStrategy;
import org.casbin.casdoor.entity.CasdoorUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class LikeMessageStrategy implements MessageStrategy {

    @Resource
    LikeMessageMapper likeMessageMapper;

    @Resource
    PostLikeMapper postLikeMapper;

    @Override
    public boolean check(String arg) {
        String user_id = BaseContext.getCurrentId();
        Long post_id = Long.parseLong(arg);
        return postLikeMapper.isLike(user_id, post_id) == 1;
    }

    @Override
    public void send(Object data) {
        CasdoorUser user = BaseContext.getCurrentUser();
        Long post_id = Long.parseLong(data.toString());
        likeMessageMapper.save(user, post_id);
    }

    @Override
    public void accept(Object data) {
        List<LikeMessageVO> messages = (List<LikeMessageVO>) data;
        List<Long> msgIdList = messages.stream().map(LikeMessage::getId).toList();
        if (msgIdList.isEmpty()) return;
        likeMessageMapper.updateStatusByIds(msgIdList);
    }

}
