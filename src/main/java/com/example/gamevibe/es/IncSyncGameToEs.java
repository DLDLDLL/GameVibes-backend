package com.example.gamevibe.es;

import com.example.gamevibe.mapper.GameMapper;
import com.example.gamevibe.model.dto.GameEsDTO;
import com.example.gamevibe.model.entity.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class IncSyncGameToEs {

    @Resource
    private GameMapper gameMapper;

    @Resource
    private GameEsDao gameEsDao;

    @Scheduled(fixedRate = 600 * 1000)
    public void run() {
        // 查询近 10 分钟内的数据
        Date tenMinutesAgoDate = new Date(new Date().getTime() - 10 * 60 * 1000L);
        List<Game> gameList = gameMapper.listGameWithDelete(tenMinutesAgoDate);
        if (CollectionUtils.isEmpty(gameList)) {
            log.info("no inc post");
            return;
        }
        List<GameEsDTO> gameEsDTOList = gameList.stream().map(GameEsDTO::objToDTO).collect(Collectors.toList());
        final int pageSize = 500;
        int total = gameEsDTOList.size();
        log.info("IncSyncGameToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            try {
                gameEsDao.saveAll(gameEsDTOList.subList(i, end));
            } catch (Exception e) {
                if(!(e.getMessage().contains("200 OK"))){
                    log.error("es增量同步错误",e);
                    return;
                }
            }
        }
        log.info("IncSyncGameToEs end, total {}", total);
    }

}
