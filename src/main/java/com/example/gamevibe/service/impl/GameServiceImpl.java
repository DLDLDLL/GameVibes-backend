package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.mapper.GameMapper;
import com.example.gamevibe.model.dto.GameDetailsDTO;
import com.example.gamevibe.model.dto.GameEsDTO;
import com.example.gamevibe.model.dto.GameQueryRequest;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.Game;
import com.example.gamevibe.model.vo.*;
import com.example.gamevibe.service.GameService;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author ZML
 * @description 针对表【Game(游戏表)】的数据库操作Service实现
 * @createDate 2024-06-19 16:17:55
 */
@Service
public class GameServiceImpl extends ServiceImpl<GameMapper, Game> implements GameService {

    @Resource
    private GameMapper gameMapper;

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public PageVO<GameRankVO> getGameRankVOPage(PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        Page<GameRankVO> gameRankVOPage = gameMapper.getGameRankVOPage(new Page<>(current, size));
        return new PageVO<GameRankVO>().objToVO(gameRankVOPage);
    }

    @Override
    public GameDetailsDTO getGameDetailsVO(Long game_id) {
        String user_id = BaseContext.getCurrentId();
        return gameMapper.getGameDetailsDTO(game_id, user_id);
    }

    @Override
    public PageVO<GameRankVO> searchFromEs(GameQueryRequest gameQueryRequest) throws IOException {
        String searchText = gameQueryRequest.getSearchText();
        // es 起始页为 0
        long current = gameQueryRequest.getCurrent() - 1;
        long pageSize = gameQueryRequest.getPageSize();
        String sortField = gameQueryRequest.getSortField();
        String sortOrder = gameQueryRequest.getSortOrder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        // 过滤
        boolQueryBuilder.filter(QueryBuilders.termQuery("is_delete", 0));
        // 按关键词检索
        if (StringUtils.isNotBlank(searchText)) {
            boolQueryBuilder.should(QueryBuilders.matchQuery("name", searchText));
            boolQueryBuilder.should(QueryBuilders.matchQuery("type", searchText));
            boolQueryBuilder.should(QueryBuilders.matchQuery("intro", searchText));
            boolQueryBuilder.minimumShouldMatch(1);
        }
        // 排序
        SortBuilder<?> sortBuilder = SortBuilders.scoreSort();
        if (StringUtils.isNotBlank(sortField)) {
            sortBuilder = SortBuilders.fieldSort(sortField);
            sortBuilder.order("ascend".equals(sortOrder) ? SortOrder.ASC : SortOrder.DESC);
        }
        // 分页
        org.springframework.data.domain.PageRequest pageRequest
                = org.springframework.data.domain.PageRequest.of((int) current, (int) pageSize);
        // 构造查询
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder)
                .withPageable(pageRequest).withSorts(sortBuilder).build();
        SearchHits<GameEsDTO> searchHits = elasticsearchRestTemplate.search(searchQuery, GameEsDTO.class);
        PageVO<GameRankVO> pageVO = new PageVO<>();
        pageVO.setTotal(searchHits.getTotalHits());
        List<GameRankVO> gameList = new ArrayList<>();
        // 结果
        if (searchHits.hasSearchHits()) {
            List<SearchHit<GameEsDTO>> searchHitList = searchHits.getSearchHits();
            List<Long> gameIdList = searchHitList.stream().map(searchHit -> searchHit.getContent().getId())
                    .collect(Collectors.toList());
            gameList = gameMapper.pageByIds(gameIdList);
        }
        pageVO.setRecords(gameList);
        return pageVO;
    }

    @Override
    public List<String> listGameName(Integer count) {
        return gameMapper.listGameName(count);
    }

}
