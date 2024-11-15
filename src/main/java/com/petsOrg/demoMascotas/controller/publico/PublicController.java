package com.petsOrg.demoMascotas.controller.publico;


import com.petsOrg.demoMascotas.dto.MascotaDTO;
import com.petsOrg.demoMascotas.dto.VersionAPI;
import com.petsOrg.demoMascotas.model.Mascota;
import com.petsOrg.demoMascotas.service.publico.PublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publico")
public class PublicController {

    @Autowired
    private PublicService mascotaPublicoService;

    @GetMapping(value = "mascotas")
    public ResponseEntity<List<MascotaDTO>> obtenerTodasLasMascotas() {
        List<MascotaDTO> mascotas = mascotaPublicoService.obtenerTodasLasMascotas();
        return ResponseEntity.ok(mascotas);
    }

    @GetMapping("mascotas/{id}")
    public ResponseEntity<MascotaDTO> obtenerMascotaPorId(@PathVariable Long id) {
        MascotaDTO mascota = mascotaPublicoService.obtenerMascotaPorId(id);
        return ResponseEntity.ok(mascota);
    }

    @GetMapping("/info")
    public ResponseEntity<VersionAPI> obtenerInformacionApi() {
        return ResponseEntity.ok(mascotaPublicoService.obtenerInformacionApi());
    }
}
