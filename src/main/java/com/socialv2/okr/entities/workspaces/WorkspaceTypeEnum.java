package com.socialv2.okr.entities.workspaces;

public enum WorkspaceTypeEnum {
    PUBLIC,
    INTERNAL,
    PRIVATE,
    PARTNER;

    public static WorkspaceTypeEnum fromString(String name) {
        for (WorkspaceTypeEnum type : WorkspaceTypeEnum.values()) {
            if (type.name().equals(name)) return type;
        }
        return null;
    }
}
