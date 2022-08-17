package com.test.authorizingapi.domain.service.exception;

import java.io.Serializable;

public class CardNotFoundException extends RuntimeException implements Serializable {

    /**
     * @param message
     */
    public CardNotFoundException(String message) {
        super(message);
    }
}
