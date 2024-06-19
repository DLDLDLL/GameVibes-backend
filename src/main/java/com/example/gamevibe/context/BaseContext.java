package com.example.gamevibe.context;

import com.example.gamevibe.model.entity.CustomUserDetails;
import org.casbin.casdoor.entity.CasdoorUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseContext {

    public static String getCurrentId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        CasdoorUser casdoorUser = customUserDetails.getCasdoorUser();
        return casdoorUser.getId();
    }

}
