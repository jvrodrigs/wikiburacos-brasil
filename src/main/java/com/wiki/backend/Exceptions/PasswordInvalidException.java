package com.wiki.backend.Exceptions;

public class PasswordInvalidException  extends RuntimeException {
    public PasswordInvalidException(){
        super("Password is invalid.");
    }
}
