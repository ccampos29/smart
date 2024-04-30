package com.solutionsystems.smart.config;

import com.solutionsystems.smart.jpa.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {
    private final String email;
    private final String password;
    private final List<GrantedAuthority> roles;
    private final boolean enabled;

    public CustomUserDetails(UserEntity user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.roles = user.getRoles().stream()
                .map(rolEntity -> new SimpleGrantedAuthority(rolEntity.getName()))
                .collect(Collectors.toList());
        this.enabled = user.getEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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
        return this.enabled;
    }
}
