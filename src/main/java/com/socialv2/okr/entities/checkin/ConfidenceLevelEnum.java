package com.socialv2.okr.entities.checkin;

import lombok.Getter;

@Getter
public enum ConfidenceLevelEnum {
    ON_TRACK("ON_TRACK"),
    NEEDS_ATTENTION("NEEDS_ATTENTION"),
    OFF_TRACK("OFF_TRACK");

    private final String key;

    ConfidenceLevelEnum(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }

}
