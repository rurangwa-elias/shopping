package edu.miu.groupx.shop.shopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex){
        return  new ResponseEntity<Object>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    //ShoppingException

}
