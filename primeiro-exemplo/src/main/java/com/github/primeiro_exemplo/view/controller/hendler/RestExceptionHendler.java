package com.github.primeiro_exemplo.view.controller.hendler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.primeiro_exemplo.model.error.ErrorMessage;
import com.github.primeiro_exemplo.model.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHendler{

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> hendlerResouceNotFoundException(ResourceNotFoundException ex){

        ErrorMessage error = new ErrorMessage("Not Found", HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
