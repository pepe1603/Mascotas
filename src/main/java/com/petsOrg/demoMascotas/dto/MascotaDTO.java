package com.petsOrg.demoMascotas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MascotaDTO {
    private Long id;
    @NotBlank(message = "El nombre del la mascota no debe estar vacio")
    private String nombre;
    @NotBlank(message = "LA especie de la mascota no debe estar vacio")
    private String especie;
    private String color;
    private String descripcion;
    @NotNull(message = "La edad de la mascota no debe ser null")
    private Integer edad;
    private LocalDateTime fechaRegistro;  // Fecha de registro
}
