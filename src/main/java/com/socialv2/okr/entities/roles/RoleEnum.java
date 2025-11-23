package com.socialv2.okr.entities.roles;

import lombok.Getter;

public enum RoleEnum {
    SUPER_ADMIN("SUPER_ADMIN"),
    WORKSPACE_ADMIN("WORKSPACE_ADMIN"),
    WORKSPACE_MEMBER("WORKSPACE_MEMBER");

    private final String key;

    RoleEnum (String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

    public String getKey() {
        return key;
    }
}