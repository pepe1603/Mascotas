package com.petsOrg.demoMascotas.service.publico;

import com.petsOrg.demoMascotas.dto.MascotaDTO;
import com.petsOrg.demoMascotas.dto.VersionAPI;

import java.util.List;

public interface PublicService {
    List<MascotaDTO> obtenerTodasLasMascotas();

    MascotaDTO obtenerMascotaPorId(Long id);


    VersionAPI obtenerInformacionApi();
}
