package com.petsOrg.demoMascotas.repository;

import com.petsOrg.demoMascotas.dto.MascotaDTO;
import com.petsOrg.demoMascotas.enums.Especie;
import com.petsOrg.demoMascotas.model.Mascota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository <Mascota, Long> {
    List<Mascota> findByEspecie(Especie especie);
    Boolean existsByNombre(String nombre);
}
