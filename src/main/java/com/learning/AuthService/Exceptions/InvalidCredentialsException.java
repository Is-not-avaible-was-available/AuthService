package com.learning.AuthService.Exceptions;

public class InvalidCredentialsException extends Exception{
    public InvalidCredentialsException(String msg){
        super(msg);
    }
}
