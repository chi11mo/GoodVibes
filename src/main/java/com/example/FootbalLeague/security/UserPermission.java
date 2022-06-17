package com.example.FootbalLeague.security;

public enum UserPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write"),

    CLUB_READ("club:read"),
    CLUB_WRITE("club:write"),

    GAME_READ("game:read"),
    GAME_WRITE("game:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
