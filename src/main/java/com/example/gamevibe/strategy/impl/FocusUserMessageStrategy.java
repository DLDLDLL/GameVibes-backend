package com.example.gamevibe.strategy.impl;

import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.FocusUserMapper;
import com.example.gamevibe.mapper.FocusUserMessageMapper;
import com.example.gamevibe.model.entity.FocusUserMessage;
import com.example.gamevibe.model.vo.FocusUserMessageVO;
import com.example.gamevibe.strategy.MessageStrategy;
import org.casbin.casdoor.entity.CasdoorUser;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class FocusUserMessageStrategy implements MessageStrategy {

    @Resource
    FocusUserMessageMapper focusUserMessageMapper;

    @Resource
    FocusUserMapper focusUserMapper;

    @Override
    public boolean check(String arg) {
        String user_id = BaseContext.getCurrentId();
        String focus_id = arg;
        return focusUserMapper.isFocus(user_id, focus_id) == 1;
    }

    @Override
    public void send(Object data) {
        CasdoorUser user = BaseContext.getCurrentUser();
        String focus_id = data.toString();
        focusUserMessageMapper.save(user, focus_id);
    }

    @Override
    public void accept(Object data) {
        List<FocusUserMessageVO> messages = (List<FocusUserMessageVO>) data;
        List<Long> msgIdList = messages.stream().map(FocusUserMessage::getId).toList();
        if (msgIdList.isEmpty()) return;
        focusUserMessageMapper.updateStatusByIds(msgIdList);
    }

}
