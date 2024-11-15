package com.petsOrg.demoMascotas.model;

import com.petsOrg.demoMascotas.enums.Color;
import com.petsOrg.demoMascotas.enums.Especie;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Especie especie;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Color color;

    private String descripcion;

    private Integer edad;

    private LocalDateTime fechaRegistro;  // Fecha de registro


}
