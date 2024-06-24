package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.FocusUserVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.service.FocusUserService;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Api(tags = "关注用户模块")
@RestController
@RequestMapping("/api/focus_user")
public class FocusUserController {

    @Resource
    private FocusUserService focusUserService;

    @ApiOperation(value = "获取关注列表", notes = "pageRequest默认为(current: 1, pageSize: 10), user_id不传则为自己")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/list/page/vo/focus")
    public BaseResponse<PageVO<FocusUserVO>> listFocusUserVOByPage(@RequestBody(required = false) @Validated PageRequest pageRequest, @RequestParam(required = false) String user_id) {
        return ResultUtils.success(focusUserService.getFocusUserVOPage(pageRequest, user_id));
    }

    @ApiOperation(value = "获取粉丝列表", notes = "pageRequest默认为(current: 1, pageSize: 10), user_id不传则为自己")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/list/page/vo/fans")
    public BaseResponse<PageVO<FocusUserVO>> listFansVOByPage(@RequestBody(required = false) @Validated PageRequest pageRequest, @RequestParam(required = false) String user_id) {
        return ResultUtils.success(focusUserService.getFansVOPage(pageRequest, user_id));
    }

    @ApiOperation(value = "关注用户")
    @ApiImplicitParam(name = "focus_id", value = "被关注的用户id", required = true, paramType = "query", dataType = "String")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/focus")
    public BaseResponse<String> focus(@RequestParam("focus_id") @NotBlank(message = "用户id不能为空") String focus_id) {
        focusUserService.focus(focus_id);
        return ResultUtils.success();
    }

    @ApiOperation(value = "取关用户")
    @ApiImplicitParam(name = "focus_id", value = "被取关的用户id", required = true, paramType = "query", dataType = "String")
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/un_focus")
    public BaseResponse<String> unFocus(@RequestParam("focus_id") @NotBlank(message = "用户id不能为空") String focus_id) {
        focusUserService.unFocus(focus_id);
        return ResultUtils.success();
    }


}
