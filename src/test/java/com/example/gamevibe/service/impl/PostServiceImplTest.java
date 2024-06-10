package com.example.gamevibe.service.impl;

import com.example.gamevibe.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

import static com.example.gamevibe.common.Constants.POST_PV_KEY;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImplTest {
    @Resource
    StringRedisTemplate stringRedisTemplate;
    @Resource
    PostService postService;

    @Test
    void getPostHotVOPage() {
        stringRedisTemplate.opsForZSet().incrementScore(POST_PV_KEY,"2",100);
        stringRedisTemplate.opsForZSet().incrementScore(POST_PV_KEY,"3",50);
        stringRedisTemplate.opsForZSet().incrementScore(POST_PV_KEY,"4",10);
        stringRedisTemplate.opsForZSet().incrementScore(POST_PV_KEY,"5",20);
        stringRedisTemplate.opsForZSet().incrementScore(POST_PV_KEY,"1",1);
    }
}