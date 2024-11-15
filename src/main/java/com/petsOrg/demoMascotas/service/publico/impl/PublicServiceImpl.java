package com.petsOrg.demoMascotas.service.publico.impl;

import com.petsOrg.demoMascotas.dto.MascotaDTO;
import com.petsOrg.demoMascotas.dto.VersionAPI;
import com.petsOrg.demoMascotas.mapper.MascotaMapper;
import com.petsOrg.demoMascotas.repository.MascotaRepository;
import com.petsOrg.demoMascotas.service.publico.PublicService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PublicServiceImpl implements PublicService {

    @Autowired
    private final MascotaRepository mascotaRepo;
    @Autowired
    private final MascotaMapper mascotaMapper;

    @Override
    public List<MascotaDTO> obtenerTodasLasMascotas() {
        return mascotaRepo.findAll().stream()
                .map(mascotaMapper::ToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDTO obtenerMascotaPorId(Long id) {
        return mascotaRepo.findById(id)
                .map(mascotaMapper::ToDTO)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));
    }

    @Override
    public VersionAPI obtenerInformacionApi() {
        double version = 1.0;
        return VersionAPI.builder()
                .name("API DE MASCTOAS --- LA PERRUNA --- ")
                .version(String.valueOf(version))
                .description("Esta API permite gestionar información sobre mascotas, incluyendo detalles como su especie, color y edad.")
                .Releassed(LocalDate.now())
                .build();
    }
}