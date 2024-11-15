package com.petsOrg.demoMascotas.service.resource.impl;

import com.petsOrg.demoMascotas.dto.MascotaDTO;
import com.petsOrg.demoMascotas.enums.Color;
import com.petsOrg.demoMascotas.enums.Especie;
import com.petsOrg.demoMascotas.exceptions.MascotaNotFoundException;
import com.petsOrg.demoMascotas.mapper.MascotaMapper;
import com.petsOrg.demoMascotas.model.Mascota;
import com.petsOrg.demoMascotas.repository.MascotaRepository;
import com.petsOrg.demoMascotas.service.resource.MascotaService;
import com.petsOrg.demoMascotas.utils.ColorValidator;
import com.petsOrg.demoMascotas.utils.DateUtils;
import com.petsOrg.demoMascotas.utils.EspecieValidator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MascotaServiceImpl implements MascotaService {

    private static final Logger log = LoggerFactory.getLogger(MascotaServiceImpl.class);
    private final MascotaRepository mascotaRepo;
    private final MascotaMapper mascotaMapper;

    @Override
    public MascotaDTO crearMascota(MascotaDTO mascotaDTO) {
        //validar especie
        Especie especieValida = EspecieValidator.validarEspecie(mascotaDTO.getEspecie());
        if (mascotaDTO.getColor() == null){
            mascotaDTO.setColor(Color.NO_DEFINIDO.name());
        }
        Mascota newMascota = mascotaMapper.ToEntity(mascotaDTO);

        newMascota.setFechaRegistro(DateUtils.obtenerFechaActual());
        newMascota.setEspecie(especieValida);

        Mascota savedMascota = mascotaRepo.save(newMascota);

        return mascotaMapper.ToDTO(savedMascota);
    }

    @Override
    public MascotaDTO obtenerMascota(Long id) {
        Mascota mascotaFounded = mascotaRepo.findById(id)
                .orElseThrow(() -> new MascotaNotFoundException("La mascota no encontrada con ID " + id));
        return mascotaMapper.ToDTO(mascotaFounded);
    }

    @Override
    public List<MascotaDTO> listarMascotas() {
        List<Mascota> listMascotas = mascotaRepo.findAll();
        return listMascotas.stream()
                .map(mascotaMapper::ToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MascotaDTO ActualizarMascota(Long id, MascotaDTO mascotaDTO) {
        //validamos especie y color antes de actualiza
        Especie especieValida = EspecieValidator.validarEspecie(mascotaDTO.getEspecie().toUpperCase());
        log.info("Especie valida: {}",especieValida);
        Color colorValido = ColorValidator.validarColor(mascotaDTO.getColor().toUpperCase());
        log.info("Colo valido: ",colorValido);

        Mascota mascotaFounded = mascotaRepo.findById(id).orElseThrow(
                () -> new MascotaNotFoundException("La actualización no pudo ser completada porque la mascota no fue encontrada con ID " + id)
        );

        mascotaMapper.updateEntity( mascotaDTO, mascotaFounded );  // Usando el método updateEntity del Mapper


        //asignamos la especie y color valido al objeto mascota
        mascotaFounded.setEspecie(especieValida);
        mascotaFounded.setColor(colorValido);
        //giardfamos mascota actualizada

        Mascota updatedMascota = mascotaRepo.save(mascotaFounded);

        return mascotaMapper.ToDTO(updatedMascota);
    }

    @Override
    public void eliminarMascota(Long id) {
        if (!mascotaRepo.existsById(id)) {
            throw new MascotaNotFoundException("Mascota no pudo ser eliminada, no se encontró ninguna coincidencia con el ID [ " + id + " ] proporcionado");
        }
        mascotaRepo.deleteById(id);
    }

    @Override
    public List<MascotaDTO> obtenerMascotasPorEspecie(Especie especie) {

        try {
            List<Mascota> mascotas = mascotaRepo.findByEspecie(especie);
            return mascotas.stream()
                    .map(mascotaMapper::ToDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            throw new RuntimeException("Error al obtener las mascotas por especie", e);
        }

    }
}
