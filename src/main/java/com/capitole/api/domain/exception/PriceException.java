package com.capitole.api.domain.exception;

public class PriceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String errorMessage;

    public PriceException(String errorMessage) {

        this.errorMessage = errorMessage;
    }

    public PriceException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
