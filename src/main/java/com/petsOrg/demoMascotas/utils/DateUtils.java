package com.petsOrg.demoMascotas.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Obtener la fecha actual
    public static LocalDateTime obtenerFechaActual() {
        return LocalDateTime.now();
    }

    // Convertir fecha a String con formato
    public static String convertirFechaAString(LocalDateTime fecha) {
        return fecha.format(formatter);
    }

    // Convertir String a LocalDateTime
    public static LocalDateTime convertirStringAFecha(String fechaStr) {
        return LocalDateTime.parse(fechaStr, formatter);
    }
}