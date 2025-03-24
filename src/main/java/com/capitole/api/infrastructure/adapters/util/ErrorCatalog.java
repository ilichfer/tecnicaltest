package com.capitole.api.infrastructure.adapters.util;

public enum ErrorCatalog {

    NOT_FOUND("404", "Resource not found"),
    BAD_REQUEST("400", "Invalid request data"),
    INTERNAL_SERVER_ERROR("500", "Internal server error"),
    UNAUTHORIZED("401", "Unauthorized access");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
