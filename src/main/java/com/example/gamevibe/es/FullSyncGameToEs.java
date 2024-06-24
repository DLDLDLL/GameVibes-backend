package com.example.gamevibe.es;

import com.example.gamevibe.model.dto.GameEsDTO;
import com.example.gamevibe.model.entity.Game;
import com.example.gamevibe.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class FullSyncGameToEs implements CommandLineRunner {

    @Resource
    private GameService gameService;

    @Resource
    private GameEsDao gameEsDao;


    @Override
    public void run(String... args) {
        List<Game> gameList = gameService.list();
        if (CollectionUtils.isEmpty(gameList)) return;
        List<GameEsDTO> gameEsDTOList = gameList.stream().map(GameEsDTO::objToDTO).collect(Collectors.toList());
        final int pageSize = 500;
        int total = gameEsDTOList.size();
        log.info("FullSyncGameToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            try {
                gameEsDao.saveAll(gameEsDTOList.subList(i, end));
            } catch (Exception e) {
                if (!(e.getMessage().contains("200 OK"))) {
                    log.error("es 全量同步错误", e);
                    return;
                }
            }
        }
        log.info("FullSyncGameToEs end, total {}", total);
    }
}
