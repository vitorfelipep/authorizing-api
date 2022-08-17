package com.test.authorizingapi.domain.service.exception;

import java.io.Serializable;

public class InvalidPassWordException extends RuntimeException implements Serializable {

    /**
     * @param message
     */
    public InvalidPassWordException(String message) {
        super(message);
    }
}
