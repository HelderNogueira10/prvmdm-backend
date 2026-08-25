package com.privguard.mdm.server.exceptions;

public class AuthenticationFailure extends RuntimeException {

    public AuthenticationFailure() {
        System.out.println("Authentication Failed.");
    }
}
