package com.example.gamevibe.context;

import com.example.gamevibe.model.entity.CustomUserDetails;
import org.casbin.casdoor.entity.CasdoorUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class BaseContext {

    public static CasdoorUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CasdoorUser casdoorUser = null;
        if (authentication.getPrincipal() instanceof CustomUserDetails customUserDetails) {
            casdoorUser = customUserDetails.getCasdoorUser();
        }
        return casdoorUser;
    }

    public static String getCurrentId() {
        CasdoorUser user = getCurrentUser();
        return Optional.ofNullable(user)
                .map(CasdoorUser::getId)
                .orElse(null);
    }

    public static String getCurrentAvatar() {
        CasdoorUser user = getCurrentUser();
        return Optional.ofNullable(user)
                .map(CasdoorUser::getAvatar)
                .orElse(null);
    }

    public static String getCurrentName() {
        CasdoorUser user = getCurrentUser();
        return Optional.ofNullable(user)
                .map(CasdoorUser::getName)
                .orElse(null);
    }

}
