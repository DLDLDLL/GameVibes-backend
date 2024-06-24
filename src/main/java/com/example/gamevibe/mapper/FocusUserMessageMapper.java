package com.example.gamevibe.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.gamevibe.model.entity.FocusUserMessage;
import com.example.gamevibe.model.vo.FocusUserMessageVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.casbin.casdoor.entity.CasdoorUser;

import java.util.List;

/**
* @author ZML
* @description 针对表【focus_user_message(关注用户消息表)】的数据库操作Mapper
* @createDate 2024-06-23 11:38:51
* @Entity .model.entity.FocusUserMessage
*/
public interface FocusUserMessageMapper extends BaseMapper<FocusUserMessage> {

    @Select("SELECT fum.id, fum.user_id, fum.user_name, fum.user_avatar, fum.create_time " +
            "FROM focus_user_message fum " +
            "WHERE fum.focus_user_id = #{user_id} AND fum.status = 0 AND fum.is_delete = 0 " +
            "ORDER BY fum.create_time DESC ")
    List<FocusUserMessageVO> listFocusUserMessage(String user_id);

    void updateStatusByIds(List<Long> msgIdList);

    @Insert("INSERT INTO focus_user_message (user_id, user_name, user_avatar, focus_user_id) VALUES " +
            "(#{user.id}, #{user.name}, #{user.avatar}, #{focus_id})")
    void save(CasdoorUser user, String focus_id);

}




