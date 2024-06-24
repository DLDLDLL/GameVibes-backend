package com.example.gamevibe.context;

import cn.hutool.extra.spring.SpringUtil;
import com.example.gamevibe.strategy.MessageStrategy;
import com.example.gamevibe.strategy.impl.CollectMessageStrategy;
import com.example.gamevibe.strategy.impl.FocusUserMessageStrategy;
import com.example.gamevibe.strategy.impl.LikeMessageStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageContext {

    private static final LikeMessageStrategy likeMessageStrategy = SpringUtil.getBean(LikeMessageStrategy.class);

    private static final CollectMessageStrategy collectMessageStrategy = SpringUtil.getBean(CollectMessageStrategy.class);

    private static final FocusUserMessageStrategy focusUserMessageStrategy = SpringUtil.getBean(FocusUserMessageStrategy.class);

    private static final Map<String, MessageStrategy> checkStrategyMap = new HashMap<>();

    static {
        checkStrategyMap.put("/api/post_like/like", likeMessageStrategy);
        checkStrategyMap.put("/api/post_collect/collect", collectMessageStrategy);
        checkStrategyMap.put("/api/focus_user/focus", focusUserMessageStrategy);
    }

    private static final Map<String, MessageStrategy> sendStrategyMap = new HashMap<>();

    static {
        sendStrategyMap.put("like", likeMessageStrategy);
        sendStrategyMap.put("collect", collectMessageStrategy);
        sendStrategyMap.put("focus", focusUserMessageStrategy);
    }

    private static final Map<String, MessageStrategy> acceptStrategyMap = new HashMap<>();

    static {
        acceptStrategyMap.put("listLikeMessage", likeMessageStrategy);
        acceptStrategyMap.put("listCollectMessage", collectMessageStrategy);
        acceptStrategyMap.put("listFocusUserMessage", focusUserMessageStrategy);
    }


    public MessageStrategy getFocusStrategy(String arg) {
        return checkStrategyMap.get(arg);
    }

    public MessageStrategy getSendStrategy(String arg) {
        return sendStrategyMap.get(arg);
    }

    public MessageStrategy getAcceptStrategy(String arg) {
        return acceptStrategyMap.get(arg);
    }

}
