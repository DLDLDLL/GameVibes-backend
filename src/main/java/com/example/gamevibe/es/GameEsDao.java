package com.example.gamevibe.es;

import com.example.gamevibe.model.dto.GameEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface GameEsDao extends ElasticsearchRepository<GameEsDTO, Long> {



}
