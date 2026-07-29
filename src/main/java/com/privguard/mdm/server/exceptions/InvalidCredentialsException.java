package com.privguard.mdm.server.exceptions;

public class InvalidCredentialsException extends RuntimeException {

    public InvalidCredentialsException() {

        System.out.println("Invalid Credentials!");
    }
}
