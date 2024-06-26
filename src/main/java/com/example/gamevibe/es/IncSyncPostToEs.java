package com.example.gamevibe.es;

import com.example.gamevibe.mapper.PostMapper;
import com.example.gamevibe.model.dto.PostEsDTO;
import com.example.gamevibe.model.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 增量同步帖子到 es
 *
 */
@Component
@Slf4j
public class IncSyncPostToEs {

    @Resource
    private PostMapper postMapper;

    @Resource
    private PostEsDao postEsDao;

    /**
     * 每十分钟执行一次
     */
    @Scheduled(fixedRate = 600 * 1000)
    public void run() {
        // 查询近 5 分钟内的数据
        Date fiveMinutesAgoDate = new Date(new Date().getTime() - 5 * 60 * 1000L);
        List<Post> postList = postMapper.listPostWithDelete(fiveMinutesAgoDate);
        if (CollectionUtils.isEmpty(postList)) {
            log.info("no inc post");
            return;
        }
        List<PostEsDTO> postEsDTOList = postList.stream()
                .map(PostEsDTO::objToDto)
                .collect(Collectors.toList());
        final int pageSize = 500;
        int total = postEsDTOList.size();
        log.info("IncSyncPostToEs start, total {}", total);
        for (int i = 0; i < total; i += pageSize) {
            int end = Math.min(i + pageSize, total);
            try {
                postEsDao.saveAll(postEsDTOList.subList(i, end));
            } catch (Exception e) {
                if(!(e.getMessage().contains("200 OK"))){
                    log.error("es增量同步错误",e);
                    return;
                }
            }
        }
        log.info("IncSyncPostToEs end, total {}", total);
    }
}
