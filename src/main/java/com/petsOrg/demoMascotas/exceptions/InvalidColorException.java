package com.petsOrg.demoMascotas.exceptions;

public class InvalidColorException extends  RuntimeException{
    public InvalidColorException(String message) {
        super(message);
    }

    public InvalidColorException(String message, Throwable cause) {
        super(message, cause);

    }
}
