package com.example.demo.security;

import com.google.common.collect.Sets;

import ch.qos.logback.core.joran.conditional.ElseAction;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.example.demo.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    TEACHER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet()),
    PARENT(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
    
    public static ApplicationUserRole getValueOf(int rawValue)
    {
    	if(rawValue == 0) return TEACHER;
    	else if(rawValue == 1) return ADMIN;
    	return PARENT;
    }
}
