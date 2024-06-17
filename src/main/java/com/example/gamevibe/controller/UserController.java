package com.example.gamevibe.controller;

import com.example.gamevibe.common.BaseResponse;
import com.example.gamevibe.model.entity.CustomUserDetails;
import org.casbin.casdoor.entity.CasdoorUser;
import org.casbin.casdoor.exception.CasdoorAuthException;
import org.casbin.casdoor.service.CasdoorAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final CasdoorAuthService casdoorAuthService;
    private final String redirectUrl;

    public UserController(CasdoorAuthService casdoorAuthService,
                          @Value("${casdoor.redirect-url}") String redirectUrl) {
        this.casdoorAuthService = casdoorAuthService;
        this.redirectUrl = redirectUrl;
    }

    @GetMapping("/redirect-url")
    public void getRedirectUrl(HttpServletResponse response) {
        try {
            String signinUrl = casdoorAuthService.getSigninUrl(redirectUrl);
            response.sendRedirect(signinUrl);
        } catch (CasdoorAuthException exception) {
            logger.error("casdoor auth exception", exception);
        } catch (IOException e) {
            logger.error("io exception", e);
        }
    }

    @GetMapping("/signin")
    public void signin(@RequestParam("code") String code, @RequestParam("state") String state, HttpServletResponse response) throws IOException {
        try {
            String token = casdoorAuthService.getOAuthToken(code, state);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
            response.sendRedirect("/index");
        } catch (CasdoorAuthException exception) {
            logger.error("casdoor auth exception", exception);
        }
    }


    public String getUid() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        CasdoorUser casdoorUser = customUserDetails.getCasdoorUser();
        return casdoorUser.getId();
    }

    @GetMapping("/api/xxx")
    public BaseResponse xxx() {
        System.out.println(getUid());
        return null;
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
}
