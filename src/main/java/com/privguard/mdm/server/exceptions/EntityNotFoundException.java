package com.privguard.mdm.server.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException() {
        super("Entity Not Found Exception");
    }
}
