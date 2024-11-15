package com.petsOrg.demoMascotas.mapper;

import com.petsOrg.demoMascotas.dto.MascotaDTO;
import com.petsOrg.demoMascotas.enums.Color;
import com.petsOrg.demoMascotas.enums.Especie;
import com.petsOrg.demoMascotas.model.Mascota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MascotaMapper {

    @Mapping(source = "especie", target = "especie", qualifiedByName = "mapEspecieToString")
    @Mapping(source = "color", target = "color", qualifiedByName = "mapColorToString")
    @Mapping(source = "id", target = "id")
    MascotaDTO ToDTO(Mascota mascota);

    @Mapping(source = "especie", target = "especie", qualifiedByName = "mapStringToEspecie")
    @Mapping(source = "color", target = "color", qualifiedByName = "mapStringToColor")
    @Mapping(source = "id", target = "id")
    Mascota ToEntity(MascotaDTO mascotaDTO);

    // Método para convertir un String a un Enum Especie
    @Named("mapStringToEspecie")
    default Especie mapStringToEspecie(String especieStr) {
        return especieStr != null ?  Especie.valueOf(especieStr.toUpperCase()) : null;
    }

    // Método para convertir un Enum Especie a String
    @Named("mapEspecieToString")
    default String mapEspecieToString(Especie especie) {
        return especie !=null ? especie.name() : null;
    }

    // Método para convertir un String a un Enum Color
    @Named("mapStringToColor")
    default Color mapStringToColor(String colorStr) {
        return colorStr != null ?  Color.valueOf(colorStr.toUpperCase()) : null;
    }

    // Método para convertir un Enum Color a String
    @Named("mapColorToString")
    default String mapColorToString(Color color) {
        return color != null ? color.name() : null ;
    }

    @Mapping(target = "fechaRegistro", ignore = true) // La fecha la asignamos en el servicio
    @Mapping(target = "id", ignore = true) //ignoraMOS EL ID EN LA ACTUALIZACION
    void updateEntity(MascotaDTO dto, @MappingTarget Mascota entity);

}
