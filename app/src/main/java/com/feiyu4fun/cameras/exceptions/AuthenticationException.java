package com.feiyu4fun.cameras.exceptions;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String error) {
        super(error);
    }
}
