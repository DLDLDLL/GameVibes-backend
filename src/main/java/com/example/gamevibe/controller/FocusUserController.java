package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.context.BaseContext;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.service.FocusUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RestController
@RequestMapping("/api/focus_user")
public class FocusUserController {

    @Resource
    private FocusUserService focusUserService;

    @GetMapping("/list/page/vo")
    public BaseResponse<?> listPostCollectVOByPage(@RequestBody(required = false) PageRequest pageRequest, @RequestParam(required = false) String query_user_id) {
        return ResultUtils.success(
                Optional.ofNullable(query_user_id)
                        .filter(id -> !id.equals(BaseContext.getCurrentId()))
                        .map(id -> focusUserService.getFocusUserVOPage(pageRequest, id))
                        .orElseGet(() -> focusUserService.getFocusUserVOPage(pageRequest))
        );
    }

    @PostMapping("/focus")
    public BaseResponse<String> focus(@RequestParam("focus_id") String focus_id) {
        focusUserService.focus(focus_id);
        return ResultUtils.success();
    }

    @PostMapping("/un_focus")
    public BaseResponse<String> unFocus(@RequestParam("focus_id") String focus_id) {
        focusUserService.unFocus(focus_id);
        return ResultUtils.success();
    }


}
