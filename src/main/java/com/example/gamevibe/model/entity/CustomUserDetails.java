package com.example.gamevibe.model.entity;

import org.casbin.casdoor.entity.CasdoorUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final CasdoorUser casdoorUser;
    private final Collection<? extends GrantedAuthority> authorities =
            AuthorityUtils.createAuthorityList("ROLE_casdoor");

    public CustomUserDetails(CasdoorUser casdoorUser) {
        this.casdoorUser = casdoorUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return casdoorUser.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public CasdoorUser getCasdoorUser() {
        return casdoorUser;
    }

}