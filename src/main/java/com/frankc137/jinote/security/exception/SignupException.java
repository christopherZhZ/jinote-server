package com.frankc137.jinote.security.exception;

public class SignupException extends BaseException {

    public SignupException(String message) {
        super(message);
    }

    public SignupException(String message, Throwable cause) {
        super(message, cause);
    }

}
