package com.example.gamevibe.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gamevibe.model.entity.CollectMessage;
import com.example.gamevibe.model.vo.CollectMessageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.casbin.casdoor.entity.CasdoorUser;

import java.util.List;

/**
* @author ZML
* @description 针对表【collect_message(收藏消息表)】的数据库操作Mapper
* @createDate 2024-06-23 11:38:51
* @Entity .model.entity.CollectMessage
*/
public interface CollectMessageMapper extends BaseMapper<CollectMessage> {

    @Select("SELECT cm.id, cm.user_id, cm.user_name, cm.user_avatar, cm.post_id, cm.post_title, " +
                "cm.post_user_id, cm.create_time " +
            "FROM collect_message cm " +
            "WHERE cm.post_user_id = #{user_id} AND cm.status = 0 AND cm.is_delete = 0 " +
            "ORDER BY cm.create_time DESC ")
    List<CollectMessageVO> listCollectMessage(String user_id);

    void updateStatusByIds(List<Long> msgIdList);

    @Insert("INSERT INTO collect_message (user_id, user_name, user_avatar, post_id, post_title, post_user_id) " +
            "SELECT #{user.id}, #{user.name}, #{user.avatar}, #{post_id}, title, user_id " +
            "FROM post " +
            "WHERE id = #{post_id} ")
    void save(CasdoorUser user, Long post_id);

}




