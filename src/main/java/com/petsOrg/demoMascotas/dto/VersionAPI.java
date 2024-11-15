package com.petsOrg.demoMascotas.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VersionAPI {
    private String name;
    private String version;
    private String description;
    private LocalDate Releassed = LocalDate.now();
}
