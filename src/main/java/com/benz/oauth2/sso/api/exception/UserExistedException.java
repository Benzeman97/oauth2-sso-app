package com.benz.oauth2.sso.api.exception;

public class UserExistedException extends RuntimeException {

    public UserExistedException(String msg){
        super(msg);
    }
}
