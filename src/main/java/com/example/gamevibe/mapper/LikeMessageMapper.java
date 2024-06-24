package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gamevibe.model.entity.LikeMessage;
import com.example.gamevibe.model.vo.LikeMessageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.casbin.casdoor.entity.CasdoorUser;

import java.util.List;

/**
* @author ZML
* @description 针对表【like_message(点赞消息表)】的数据库操作Mapper
* @createDate 2024-06-23 11:38:51
* @Entity .model.entity.LikeMessage
*/
public interface LikeMessageMapper extends BaseMapper<LikeMessage> {

    @Select("SELECT lm.id, lm.user_id, lm.user_name, lm.user_avatar, lm.post_id, lm.post_title, " +
                "lm.post_user_id, lm.create_time " +
            "FROM like_message lm " +
            "WHERE lm.post_user_id = #{user_id} AND lm.status = 0 AND lm.is_delete = 0 " +
            "ORDER BY lm.create_time DESC ")    
    List<LikeMessageVO> listLikeMessage(String user_id);

    void updateStatusByIds(List<Long> msgIdList);

    @Insert("INSERT INTO like_message (user_id, user_name, user_avatar, post_id, post_title, post_user_id) " +
            "SELECT #{user.id}, #{user.name}, #{user.avatar}, #{post_id}, title, user_id " +
            "FROM post " +
            "WHERE id = #{post_id} ")
    void save(CasdoorUser user, Long post_id);
    
}




