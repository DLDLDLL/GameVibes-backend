package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.common.ResultUtils;
import com.example.gamevibe.model.dto.PageRequest;
import com.example.gamevibe.model.vo.MyPostVO;
import com.example.gamevibe.model.vo.PageVO;
import com.example.gamevibe.model.vo.UserVO;
import com.example.gamevibe.service.UserService;
import io.swagger.annotations.*;
import org.casbin.casdoor.service.CasdoorAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api(tags = "用户模块")
@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final CasdoorAuthService casdoorAuthService;

    private final String redirectUrl;

    @Resource
    private UserService userService;

    public UserController(CasdoorAuthService casdoorAuthService,
                          @Value("${casdoor.redirect-url}") String redirectUrl) {
        this.casdoorAuthService = casdoorAuthService;
        this.redirectUrl = redirectUrl;
    }

    @ApiOperation(value = "获取用户首页信息")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/user_info")
    public BaseResponse<UserVO> getUserInfo() {
        return ResultUtils.success(userService.getUserInfo());
    }

    @ApiOperation(value = "更新用户信息")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "avatar", value = "头像", paramType = "query"),
            @ApiImplicitParam(name = "nick_name", value = "昵称", paramType = "query")
    })
    @ApiResponse(code = 0, message = "ok")
    @PostMapping("/save")
    public BaseResponse<String> save(@RequestParam(required = false) String avatar, @RequestParam(required = false) String nick_name) {
        userService.save(avatar, nick_name);
        return ResultUtils.success();
    }

    @ApiOperation(value = "我的帖子")
    @ApiResponse(code = 0, message = "ok")
    @GetMapping("/list/page/vo")
    public BaseResponse<PageVO<MyPostVO>> listMyPostVOByPage(@RequestBody(required = false) @Validated PageRequest pageRequest) {
        return ResultUtils.success(userService.getMyPostVOPage(pageRequest));

    }


}


    /*    @GetMapping("/api/redirect-url")
    public BaseResponse getRedirectUrl() {
        try {
            String signinUrl = casdoorAuthService.getSigninUrl(redirectUrl);
            return ResultUtils.success(signinUrl);
        } catch (CasdoorAuthException exception) {
            logger.error("casdoor auth exception", exception);
            return ResultUtils.error(exception.getMessage());
        }
    }*/

/*    @GetMapping("/api/signin")
    public BaseResponse signin(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response) {
        try {
            String token = casdoorAuthService.getOAuthToken(code, state);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);

            return ResultUtils.success(null);
        } catch (CasdoorAuthException exception) {
            logger.error("casdoor auth exception", exception);
            return ResultUtils.error(exception.getMessage());
        }
    }*/

//    @GetMapping("/redirect-url")
//    public void getRedirectUrl(HttpServletResponse response) {
//        try {
//            String signinUrl = casdoorAuthService.getSigninUrl(redirectUrl);
//            response.sendRedirect(signinUrl);
//        } catch (CasdoorAuthException exception) {
//            logger.error("casdoor auth exception", exception);
//        } catch (IOException e) {
//            logger.error("io exception", e);
//        }
//    }
//
//    @GetMapping("/signin")
//    public void signin(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response) throws IOException {
//        try {
//            String token = casdoorAuthService.getOAuthToken(code, state);
//            Cookie cookie = new Cookie("token", token);
//            response.addCookie(cookie);
//            response.sendRedirect("/index");
//        } catch (CasdoorAuthException exception) {
//            logger.error("casdoor auth exception", exception);
//        }
//    }
