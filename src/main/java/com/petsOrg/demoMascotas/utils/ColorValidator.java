package com.petsOrg.demoMascotas.utils;

import com.petsOrg.demoMascotas.enums.Color;
import com.petsOrg.demoMascotas.enums.Especie;
import com.petsOrg.demoMascotas.exceptions.InvalidEspecieException;

public class ColorValidator {

    // Método para validar el String y convertirlo a Enum Color
    public static Color validarColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new InvalidEspecieException("El color no puede ser nula o estar vacía.");
        }

        try {
            // Intentamos convertir el String a Enum Color
            return Color.valueOf(color.toUpperCase());  // Convertimos a mayúsculas para manejar posibles inconsistencias
        } catch (IllegalArgumentException e) {
            // Si no se encuentra una coincidencia, lanzamos una excepción personalizada
            throw new InvalidEspecieException("Especie inválida: " + color +" " +
                    "\n Opciones validas: \n BLANCO," +
                    "    NEGRO," +
                    "    MARRON," +
                    "    GRIS," +
                    "    MULTICOLOR," +
                    "    NO_DEFINIDO ]");
        }
    }
}
