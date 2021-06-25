package com.benz.oauth2.sso.api.exception;

import com.benz.oauth2.sso.api.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExistedExceptionHandler {

    @ExceptionHandler(UserExistedException.class)
    public ResponseEntity<ErrorMessage> toResponse(UserExistedException ex){
        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.CONFLICT.value(),ex.getMessage(),"www.benz.com");
        return new ResponseEntity<>(errorMessage,HttpStatus.CONFLICT);
    }
}
