package com.example.gamevibe.context;

import com.example.gamevibe.model.entity.CustomUserDetails;
import org.casbin.casdoor.entity.CasdoorUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseContext {

    public static CasdoorUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        return customUserDetails.getCasdoorUser();
    }

    public static String getCurrentId() {
        return getCurrentUser().getId();
    }

    public static String getCurrentAvatar() {
        return getCurrentUser().getAvatar();
    }

    public static String getCurrentName() {
        return getCurrentUser().getName();
    }


}
