package com.learning.AuthService.Exceptions;

public class AlreadyExistsException extends Exception{
    public AlreadyExistsException(String msg){
        super(msg);
    }
}
