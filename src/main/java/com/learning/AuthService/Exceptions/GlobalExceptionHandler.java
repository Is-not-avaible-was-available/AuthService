package com.learning.AuthService.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException notFoundException){
        return new ResponseEntity<>(
                new ExceptionDTO(notFoundException.getMessage(),
                HttpStatus.NOT_FOUND),
                   HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionDTO> handleAlreadyExistsException(AlreadyExistsException alreadyExistsException){
        return new ResponseEntity<>(
                new ExceptionDTO(alreadyExistsException.getMessage(),
                HttpStatus.BAD_REQUEST),
                  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ExceptionDTO> handleInvalidCredentialException(InvalidCredentialsException invalidCredentialsException){
        return new ResponseEntity<>(
                new ExceptionDTO(
                invalidCredentialsException.getMessage(),
                HttpStatus.BAD_REQUEST),
                     HttpStatus.BAD_REQUEST);
    }
}
