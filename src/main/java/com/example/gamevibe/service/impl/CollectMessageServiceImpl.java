package com.example.gamevibe.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.CollectMessageMapper;
import com.example.gamevibe.model.entity.CollectMessage;
import com.example.gamevibe.model.vo.CollectMessageVO;
import com.example.gamevibe.service.CollectMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author ZML
* @description 针对表【collect_message(收藏消息表)】的数据库操作Service实现
* @createDate 2024-06-23 11:38:51
*/
@Service
public class CollectMessageServiceImpl extends ServiceImpl<CollectMessageMapper, CollectMessage> implements CollectMessageService {

    @Resource
    private CollectMessageMapper collectMessageMapper;

    @Override
    public List<CollectMessageVO> listCollectMessage() {
        String user_id = BaseContext.getCurrentId();
        return collectMessageMapper.listCollectMessage(user_id);
    }


}




