package com.example.gamevibe.strategy.impl;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.CollectMessageMapper;
import com.example.gamevibe.mapper.PostCollectMapper;
import com.example.gamevibe.model.entity.CollectMessage;
import com.example.gamevibe.model.vo.CollectMessageVO;
import com.example.gamevibe.strategy.MessageStrategy;
import org.casbin.casdoor.entity.CasdoorUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class CollectMessageStrategy implements MessageStrategy {

    @Resource
    CollectMessageMapper collectMessageMapper;

    @Resource
    PostCollectMapper postCollectMapper;

    @Override
    public boolean check(String arg) {
        String user_id = BaseContext.getCurrentId();
        Long post_id = Long.parseLong(arg);
        return postCollectMapper.isCollect(user_id, post_id) == 1;
    }

    @Override
    public void send(Object data) {
        CasdoorUser user = BaseContext.getCurrentUser();
        Long post_id = Long.parseLong(data.toString());
        collectMessageMapper.save(user, post_id);
    }

    @Override
    public void accept(Object data) {
        List<CollectMessageVO> messages = (List<CollectMessageVO>) data;
        List<Long> msgIdList = messages.stream().map(CollectMessage::getId).toList();
        if (msgIdList.isEmpty()) return;
        collectMessageMapper.updateStatusByIds(msgIdList);
    }


}
