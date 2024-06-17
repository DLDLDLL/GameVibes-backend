package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.service.FocusUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/focus_user")
public class FocusUserController {

    @Resource
    private FocusUserService focusUserService;

    @GetMapping("/list/page/vo")
    public BaseResponse<?> listPostCollectVOByPage(PageRequest pageRequest) {
        return ResultUtils.success(focusUserService.getFocusUserVOPage(pageRequest));
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
