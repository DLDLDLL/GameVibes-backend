package com.example.gamevibe.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gamevibe.mapper.PostMapper;
import com.example.gamevibe.model.entity.News;
import com.example.gamevibe.model.entity.Post;
import com.example.gamevibe.service.NewsService;
import com.example.gamevibe.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static com.example.gamevibe.common.Constants.POST_PV_KEY;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImplTest {
    @Resource
    PostService postService;
    @Resource
    NewsService newsService;

    @Test
    void addNews() {
        News news = new News();
        news.setTitle("2024夏日游戏节资讯汇总，你想看的都在这!");
        news.setContent("news content");
        news.setImages("https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%AE%9D%E5%8F%AF%E6%A2%A6&step_word=&lid=9550131787730797085&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3961126115,158086461&os=3254334051,1214124167&simid=3961126115,158086461&pn=16&rn=1&di=7360350738658099201&ln=1870&fr=&fmq=1717918837755_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=1e&objurl=https%3A%2F%2Fp3.toutiaoimg.com%2Fpgc-image%2Fc0998f07af504c44b64a39ecce06e1a8~noop.image%3F_iz%3D58558%26from%3Darticle.pc_detail%26x-expires%3D1676603270%26xature%3DlH94qXzoV3wUKJt7b2THCO5U0%252BA%253D&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwzLDEsMiw2LDQsNSw4LDcsOQ%3D%3D");
        news.setType("PC游戏");
        news.setUser_id("1");
        newsService.save(news);
    }

    @Test
    void addPost() {
        Post post = new Post();
        post.setLocation("广东");
        post.setTitle("[黑神话:悟空]虽已证实D加密但可以期待一下");
        post.setContent("post content");
        post.setImages("https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E5%AE%9D%E5%8F%AF%E6%A2%A6&step_word=&lid=9550131787730797085&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3961126115,158086461&os=3254334051,1214124167&simid=3961126115,158086461&pn=16&rn=1&di=7360350738658099201&ln=1870&fr=&fmq=1717918837755_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=1e&objurl=https%3A%2F%2Fp3.toutiaoimg.com%2Fpgc-image%2Fc0998f07af504c44b64a39ecce06e1a8~noop.image%3F_iz%3D58558%26from%3Darticle.pc_detail%26x-expires%3D1676603270%26xature%3DlH94qXzoV3wUKJt7b2THCO5U0%252BA%253D&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwzLDEsMiw2LDQsNSw4LDcsOQ%3D%3D");
        post.setType("幻兽帕鲁");
        post.setUser_id("1");
        postService.save(post);
    }

}