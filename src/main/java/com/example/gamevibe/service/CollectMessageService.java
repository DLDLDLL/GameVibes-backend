package com.example.gamevibe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.entity.CollectMessage;
import com.example.gamevibe.model.vo.CollectMessageVO;

import java.util.List;

/**
* @author ZML
* @description 针对表【collect_message(收藏消息表)】的数据库操作Service
* @createDate 2024-06-23 11:38:51
*/
public interface CollectMessageService extends IService<CollectMessage> {

    List<CollectMessageVO> listCollectMessage();
}
