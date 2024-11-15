package com.petsOrg.demoMascotas.service.resource;

import com.petsOrg.demoMascotas.dto.MascotaDTO;
import com.petsOrg.demoMascotas.enums.Especie;
import com.petsOrg.demoMascotas.model.Mascota;

import java.util.List;

public interface MascotaService {


    MascotaDTO crearMascota(MascotaDTO mascotaDTO);

    MascotaDTO obtenerMascota(Long id);

    List<MascotaDTO> listarMascotas();

    MascotaDTO ActualizarMascota(Long id, MascotaDTO mascotaDTO);

    void eliminarMascota(Long id);

    List<MascotaDTO> obtenerMascotasPorEspecie(Especie especie);
}
