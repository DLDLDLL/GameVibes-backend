package com.example.gamevibe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.PostCollectVO;
import com.example.gamevibe.model.entity.PostCollect;
import com.example.gamevibe.model.vo.PageVO;
import java.util.List;

/**
* @author ZML
* @description 针对表【post_collect(帖子收藏表)】的数据库操作Service
* @createDate 2024-06-11 21:18:53
*/
public interface PostCollectService extends IService<PostCollect> {

    PageVO<List<PostCollectVO>, PostCollectVO> getCollectPostVOPage(PageRequest pageRequest);

    void collect(String post_id);

    void unCollect(String post_id);
}
