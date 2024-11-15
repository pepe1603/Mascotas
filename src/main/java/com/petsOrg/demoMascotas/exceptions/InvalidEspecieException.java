package com.petsOrg.demoMascotas.exceptions;

public class InvalidEspecieException extends  RuntimeException{
    public InvalidEspecieException(String message) {
        super(message);
    }

    public InvalidEspecieException(String message, Throwable cause) {
        super(message, cause);

    }
}
