package com.petsOrg.demoMascotas.controller.resource;

import com.petsOrg.demoMascotas.dto.MascotaDTO;
import com.petsOrg.demoMascotas.enums.Especie;
import com.petsOrg.demoMascotas.exceptions.InvalidEspecieException;
import com.petsOrg.demoMascotas.service.resource.MascotaService;
import com.petsOrg.demoMascotas.utils.EspecieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    // Crear una nueva mascota
    @PostMapping
    public ResponseEntity<MascotaDTO> crearMascota(@RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO nuevaMascota = mascotaService.crearMascota(mascotaDTO);
        return ResponseEntity.ok(nuevaMascota);
    }

    // Obtener todas las mascotas
    @GetMapping
    public ResponseEntity<List<MascotaDTO>> obtenerTodasLasMascotas() {
        List<MascotaDTO> mascotas = mascotaService.listarMascotas();
        return ResponseEntity.ok(mascotas);
    }

    // Obtener mascota por ID
    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> obtenerMascotaPorId(@PathVariable Long id) {
        MascotaDTO mascota = mascotaService.obtenerMascota(id);
        return ResponseEntity.ok(mascota);
    }

    // Obtener mascotas por especie
    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<MascotaDTO>> obtenerMascotasPorEspecie(@PathVariable String especie) {
            // Validar la especie usando el validador
            Especie especieValida = EspecieValidator.validarEspecie(especie);
            List<MascotaDTO> mascotas = mascotaService.obtenerMascotasPorEspecie(especieValida);
            return ResponseEntity.ok(mascotas);

    }


    // Actualizar una mascota por ID
    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> actualizarMascota(@PathVariable Long id, @RequestBody MascotaDTO mascotaDTO) {
        MascotaDTO mascotaActualizada = mascotaService.ActualizarMascota(id, mascotaDTO);
        return ResponseEntity.ok(mascotaActualizada);
    }

    // Eliminar una mascota por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Long id) {
        mascotaService.eliminarMascota(id);
        return ResponseEntity.noContent().build();  // Devuelve un status 204 No Content
    }
}
