package com.petsOrg.demoMascotas.exceptions;

public class InvalidMascotaException extends  RuntimeException{
    public InvalidMascotaException(String message) {
        super(message);
    }
}
