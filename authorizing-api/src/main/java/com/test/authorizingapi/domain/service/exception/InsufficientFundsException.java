package com.test.authorizingapi.domain.service.exception;

import java.io.Serial;

public class InsufficientFundsException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -2454812069332138945L;

    /**
     * @param message
     */
    public InsufficientFundsException(String message) {
        super(message);
    }
}
