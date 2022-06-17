package com.example.FootbalLeague.Model;

import com.example.FootbalLeague.security.UserPermission;
import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    PLAYER(Sets.newHashSet()),
    MODERATOR(Sets.newHashSet(UserPermission.USER_READ)),
    ADMIN(Sets.newHashSet(UserPermission.USER_READ,UserPermission.USER_WRITE));

    private final Set<UserPermission> permissions;

    Role(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
    public Set<UserPermission> getPermissions(){
        return permissions;
    }
    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> permissions =
        getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority((permission.getPermission())))
                        .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority(("ROLE_"+this.name())));
        return permissions;
    }
}
