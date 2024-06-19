package com.example.gamevibe.es;

import com.example.gamevibe.model.dto.PostEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
/**
 * 帖子 ES 操作
 *
 */
public interface PostEsDao extends ElasticsearchRepository<PostEsDTO, Long> {

}