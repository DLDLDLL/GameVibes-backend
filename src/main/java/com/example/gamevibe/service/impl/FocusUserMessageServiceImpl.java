package com.example.gamevibe.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.FocusUserMessageMapper;
import com.example.gamevibe.model.entity.FocusUserMessage;
import com.example.gamevibe.model.vo.FocusUserMessageVO;
import com.example.gamevibe.service.FocusUserMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ZML
* @description 针对表【focus_user_message(关注用户消息表)】的数据库操作Service实现
* @createDate 2024-06-23 11:38:51
*/
@Service
public class FocusUserMessageServiceImpl extends ServiceImpl<FocusUserMessageMapper, FocusUserMessage> implements FocusUserMessageService {

    @Resource
    FocusUserMessageMapper focusUserMessageMapper;

    @Override
    public List<FocusUserMessageVO> listFocusUserMessage() {
        String user_id = BaseContext.getCurrentId();
        return focusUserMessageMapper.listFocusUserMessage(user_id);
    }


}




