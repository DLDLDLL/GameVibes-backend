package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.entity.FocusUser;
import com.example.gamevibe.model.vo.FocusUserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
* @author ZML
* @description 针对表【focus_user(关注用户表)】的数据库操作Mapper
* @createDate 2024-06-11 21:18:53
* @Entity .model.entity.FocusUser
*/
public interface FocusUserMapper extends BaseMapper<FocusUser> {

    @Select("SELECT u.user_id, u.nick_name, u.avatar, u.intro, 0 AS is_focus " +
            "FROM focus_user fu " +
            "INNER JOIN user u ON fu.focused_id = u.user_id AND u.is_delete = 0 " +
            "WHERE fu.user_id = #{user_id} AND fu.state = 1 " +
            "ORDER BY fu.update_time DESC ")
    Page<FocusUserVO> getFocusUserVOPage(String user_id, Page<?> page);

    @Select("SELECT u.user_id, u.nick_name, u.avatar, u.intro, 0 AS is_focus " +
            "FROM focus_user fu " +
            "INNER JOIN user u ON fu.user_id = u.user_id AND u.is_delete = 0 " +
            "WHERE fu.focused_id = #{user_id} AND fu.state = 1 " +
            "ORDER BY fu.update_time DESC ")
    Page<FocusUserVO> getFansVOPage(String user_id, Page<?> page);

    @Select("SELECT focused_id FROM focus_user WHERE user_id = #{user_id} AND state = 1 ")
    List<String> getFids(String user_id);


    @Insert("INSERT INTO focus_user (user_id, focused_id) VALUES (#{user_id}, #{focused_id}) ON DUPLICATE KEY UPDATE state = 1")
    void saveFocus(String user_id, String focused_id);

    @Update("UPDATE focus_user SET state = 0 WHERE user_id = #{user_id} AND focused_id = #{focused_id}")
    void cancelFocus(String user_id, String focused_id);

    @Update("UPDATE user SET focus_count = focus_count + #{num} WHERE user_id = #{user_id} ")
    void updateFocusCount(String user_id, Integer num);

    @Update("UPDATE user SET fans_count = fans_count + #{num} WHERE user_id = #{user_id} ")
    void updateFansCount(String user_id, Integer num);

    @Select("SELECT COUNT(*) FROM focus_user WHERE user_id = #{user_id} AND focused_id = #{focus_id} AND state = 1 ")
    int isFocus(String user_id, String focus_id);

}




