package com.example.gamevibe.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.entity.FocusUser;
import com.example.gamevibe.model.vo.FocusUserVO;
import com.example.gamevibe.model.vo.PageVO;


/**
* @author ZML
* @description 针对表【focus_user(关注用户表)】的数据库操作Service
* @createDate 2024-06-11 21:18:53
*/
public interface FocusUserService extends IService<FocusUser> {

    PageVO<FocusUserVO> getFocusUserVOPage(PageRequest pageRequest, String user_id);

    PageVO<FocusUserVO> getFansVOPage(PageRequest pageRequest, String user_id);

    void focus(String focus_id);

    void unFocus(String focus_id);
}
