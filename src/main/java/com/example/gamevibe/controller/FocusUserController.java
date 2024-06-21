package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.FocusUserVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.FocusUserService;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@Api(tags = "关注用户模块")
@RestController
@RequestMapping("/api/focus_user")
public class FocusUserController {

    @Resource
    private FocusUserService focusUserService;

    @ApiOperation(value = "获取关注用户列表", notes = "pageRequest默认为(current: 1, pageSize: 10), query_user_id不传则为自己")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/list/page/vo")
    public BaseResponse<PageVO<FocusUserVO>> listFocusUserVOByPage(@RequestBody(required = false) PageRequest pageRequest, @RequestParam(required = false) String query_user_id) {
        return ResultUtils.success(
                Optional.ofNullable(query_user_id)
                        .filter(id -> !id.equals(BaseContext.getCurrentId()))
                        .map(id -> focusUserService.getFocusUserVOPage(pageRequest, id))
                        .orElseGet(() -> focusUserService.getFocusUserVOPage(pageRequest))
        );
    }

    @ApiOperation(value = "关注用户")
    @ApiImplicitParam(name = "focus_id", value = "被关注的用户id", required = true, paramType = "query", dataType = "String")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/focus")
    public BaseResponse<String> focus(@RequestParam("focus_id") String focus_id) {
        focusUserService.focus(focus_id);
        return ResultUtils.success();
    }

    @ApiOperation(value = "取关用户")
    @ApiImplicitParam(name = "focus_id", value = "被取关的用户id", required = true, paramType = "query", dataType = "String")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/un_focus")
    public BaseResponse<String> unFocus(@RequestParam("focus_id") String focus_id) {
        focusUserService.unFocus(focus_id);
        return ResultUtils.success();
    }


}
