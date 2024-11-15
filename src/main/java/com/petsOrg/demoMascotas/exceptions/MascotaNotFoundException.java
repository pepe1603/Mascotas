package com.petsOrg.demoMascotas.exceptions;

public class MascotaNotFoundException extends RuntimeException{
    public MascotaNotFoundException(String message) {
        super(message);
    }
}
