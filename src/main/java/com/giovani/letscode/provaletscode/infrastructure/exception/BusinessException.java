package com.giovani.letscode.provaletscode.infrastructure.exception;

public class BusinessException extends Exception {

    public BusinessException(String customErrorMessage) {
        super(customErrorMessage);
    }
}
