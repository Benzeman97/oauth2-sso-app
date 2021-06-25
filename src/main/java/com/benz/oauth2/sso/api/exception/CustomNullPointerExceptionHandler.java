package com.benz.oauth2.sso.api.exception;

import com.benz.oauth2.sso.api.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomNullPointerExceptionHandler {

    @ExceptionHandler(CustomNullPointerException.class)
    public ResponseEntity<ErrorMessage> toResponse(CustomNullPointerException ex){
        ErrorMessage errorMessage=new ErrorMessage(HttpStatus.BAD_REQUEST.value(),ex.getMessage(),"www.benz.com");
        return new ResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
