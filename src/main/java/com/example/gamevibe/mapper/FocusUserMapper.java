package com.example.gamevibe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.gamevibe.model.entity.FocusUser;
import com.example.gamevibe.model.vo.FocusUserVO;
import org.apache.ibatis.annotations.*;

/**
* @author ZML
* @description 针对表【focus_user(关注用户表)】的数据库操作Mapper
* @createDate 2024-06-11 21:18:53
* @Entity .model.entity.FocusUser
*/
@Mapper
public interface FocusUserMapper extends BaseMapper<FocusUser> {

    @Select("SELECT u.user_id, u.nick_name, u.avatar, u.intro, 1 AS is_focus " +
            "FROM focus_user fu " +
            "INNER JOIN user u ON fu.focused_id = u.user_id AND u.is_delete = 0 " +
            "WHERE fu.user_id = #{user_id} AND fu.state = 1 " +
            "ORDER BY fu.update_time DESC ")
    Page<FocusUserVO> getFocusUserVOPage(String user_id, Page<?> page);

    @Select("SELECT u.user_id, u.nick_name, u.avatar, u.intro, IF(fu.user_id IS NULL, 0, 1) AS is_focus " +
            "FROM focus_user fuo " +
            "LEFT JOIN user u ON fuo.focused_id = u.user_id " +
            "LEFT JOIN focus_user fu ON fu.focused_id = u.user_id AND fu.user_id = #{user_id} " +
            "WHERE fuo.user_id = #{query_user_id} AND fuo.state = 1 " +
            "ORDER BY fuo.update_time DESC ")
    Page<FocusUserVO> getFocusUserVOPage(String user_id, String query_user_id, Page<?> page);

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




