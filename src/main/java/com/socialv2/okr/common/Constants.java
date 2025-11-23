package com.socialv2.okr.common;

import lombok.Getter;

public final class Constants {
    @Getter
    public enum ERROR_MESSAGE_KEY {
        INVALID_ARGUMENT("error.invalid.argument"),
        ERROR_GENERAL("error.general");

        private final String key;

        ERROR_MESSAGE_KEY (String key) {
            this.key = key;
        }
    }
}
