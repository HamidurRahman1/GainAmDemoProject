package com.hamidur.gainam.auth.service;

import com.hamidur.gainam.auth.models.Role;
import com.hamidur.gainam.auth.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AppUserDetails implements UserDetails
{
    private final User user;
    private static final String ROLE_PREFIX = "ROLE_";

    public AppUserDetails(final User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = user.getRoles();
        Set<SimpleGrantedAuthority> grantedRoles = new HashSet<>();
        roles.forEach( e -> grantedRoles.add(new SimpleGrantedAuthority(ROLE_PREFIX + e.getRole())));
        return grantedRoles;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
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
}
