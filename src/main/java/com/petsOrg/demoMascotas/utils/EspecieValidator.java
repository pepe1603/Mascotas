package com.petsOrg.demoMascotas.utils;

import com.petsOrg.demoMascotas.enums.Especie;
import com.petsOrg.demoMascotas.exceptions.InvalidEspecieException;

public class EspecieValidator {

    // Método para validar el String y convertirlo a Enum Especie
    public static Especie validarEspecie(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            throw new InvalidEspecieException("La especie no puede ser nula o vacía.");
        }

        try {
            // Intentamos convertir el String a Enum Especie
            return Especie.valueOf(especie.toUpperCase());  // Convertimos a mayúsculas para manejar posibles inconsistencias
        } catch (IllegalArgumentException e) {
            // Si no se encuentra una coincidencia, lanzamos una excepción personalizada
            throw new InvalidEspecieException("Especie inválida: " + especie +" " +
                    "\n Opciones validas: \n [   PERRO," +
                    "    GATO," +
                    "    HURON," +
                    "    CONEJO," +
                    "    PEZ," +
                    "    DESCONOCIDO ]");
        }
    }
}
