package com.petsOrg.demoMascotas.service.impl;

import com.petsOrg.demoMascotas.dto.MascotaDTO;
import com.petsOrg.demoMascotas.enums.Color;
import com.petsOrg.demoMascotas.enums.Especie;
import com.petsOrg.demoMascotas.exceptions.MascotaNotFoundException;
import com.petsOrg.demoMascotas.mapper.MascotaMapper;
import com.petsOrg.demoMascotas.model.Mascota;
import com.petsOrg.demoMascotas.repository.MascotaRepository;
import com.petsOrg.demoMascotas.service.resource.MascotaService;

import com.petsOrg.demoMascotas.service.resource.impl.MascotaServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MascotaServiceImplTest {

    @InjectMocks
    private MascotaServiceImpl mascotaService;

    @Mock
    private MascotaRepository mascotaRepo;

    @Mock
    private MascotaMapper mascotaMapper;

    //objetos de prueba

    private MascotaDTO mascotaDTO;
    private Mascota mascota;

    @BeforeEach
    void setUp() {
        //inicializa los mocks
        MockitoAnnotations.openMocks(this);

        //configuracion de objetosd de prueba
        mascotaDTO = new MascotaDTO(1L, "Firulais", "PERRO", "MARRON", "Un perro muy juguetón", 5, null);
        mascota = new Mascota(null, "Firulais", Especie.PERRO, Color.MARRON, "Un perro muy juguetón", 5, null);

    }

    //------------- -MEtodo crear mascota-------------
    @Test
    void TestCrearMascota(){
        //confirmamos comportamiento de los mocks
        when(mascotaMapper.ToEntity(mascotaDTO)).thenReturn(mascota); //cuando se convierte a entidad, se devuelve un objeto mascota
        when(mascotaRepo.save(mascota)).thenReturn(mascota); //cuando se guarde la mascota , se devuelve el mismo objeto
        when(mascotaMapper.ToDTO(mascota)).thenReturn(mascotaDTO); //cuando se convierta de entidad a DTO, se devuelve el DTO

        //lalammos al metodo dels ervicio
        MascotaDTO result = mascotaService.crearMascota(mascotaDTO);

        //verificacion de resultados
        assertNotNull(result); // assegura que el resulñtado no sea nulo
        assertEquals("Firulais", result.getNombre()); //Asegfura que el nombre es corecto+


        //verificacion de interacciones con los mocks
        verify(mascotaMapper, times(1)).ToEntity(mascotaDTO); //verifica que se halla llamado a toEntttity una vez
        verify(mascotaRepo, times(1)).save(mascota); //verifica que se haya a save una vez
        verify(mascotaMapper, times(1)).ToDTO(mascota);

    }
    @Test
    void testCrearMascotaThrowsException() {
        when(mascotaMapper.ToEntity(mascotaDTO)).thenThrow(new RuntimeException("Error de mapeo"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mascotaService.crearMascota(mascotaDTO);
        });

        assertEquals("Error de mapeo", exception.getMessage());
    }

    //-------------- Metodo para obtener mascota -------------------

    @Test
    void testObtenerMascota() {
        // Configuración del mock
        when(mascotaRepo.findById(1L)).thenReturn(java.util.Optional.of(mascota)); // Retorna una mascota si el ID existe
        when(mascotaMapper.ToDTO(mascota)).thenReturn(mascotaDTO); // Mapeo a DTO

        // Llamada al método
        MascotaDTO result = mascotaService.obtenerMascota(1L);

        // Verificaciones
        assertNotNull(result);
        assertEquals("Firulais", result.getNombre()); // Verifica el nombre

        // Verifica las interacciones
        verify(mascotaRepo, times(1)).findById(1L);
        verify(mascotaMapper, times(1)).ToDTO(mascota);
    }

    @Test
    void testObtenerMascotaThrowsException() {
        // Configuración del mock para que no se encuentre la mascota
        when(mascotaRepo.findById(1L)).thenReturn(java.util.Optional.empty());

        // Llamada al método y verificación de la excepción
        MascotaNotFoundException exception = assertThrows(MascotaNotFoundException.class, () -> {
            mascotaService.obtenerMascota(1L);
        });

        assertEquals("La mascota no encontrada con ID 1", exception.getMessage());
    }

    //----------- Metodo para Listtar masctyoas -----------
    @Test
    void testListarMascotas() {
        // Configuración del mock
        when(mascotaRepo.findAll()).thenReturn(List.of(mascota)); // Retorna una lista de una mascota
        when(mascotaMapper.ToDTO(mascota)).thenReturn(mascotaDTO); // Mapea la mascota a DTO

        // Llamada al método
        List<MascotaDTO> result = mascotaService.listarMascotas();

        // Verificaciones
        assertNotNull(result);
        assertEquals(1, result.size()); // Verifica que la lista tiene 1 elemento
        assertEquals("Firulais", result.get(0).getNombre()); // Verifica el nombre

        // Verificaciones de las interacciones
        verify(mascotaRepo, times(1)).findAll();
        verify(mascotaMapper, times(1)).ToDTO(mascota);
    }

    //----------- Metopdo Actualizar Mawsctoa -----------------
    @Test
    void testActualizarMascota() {
        // Configuración de los mocks
        when(mascotaRepo.findById(1L)).thenReturn(java.util.Optional.of(mascota)); // Retorna la mascota existente
        when(mascotaMapper.ToDTO(mascota)).thenReturn(mascotaDTO); // Mapeo de entidad a DTO
        when(mascotaRepo.save(mascota)).thenReturn(mascota); // Guardar la mascota actualizada

        // Llamada al método
        MascotaDTO result = mascotaService.ActualizarMascota(1L, mascotaDTO);

        // Verificaciones
        assertNotNull(result);
        assertEquals("Firulais", result.getNombre()); // Verifica el nombre

        // Verificación de las interacciones
        verify(mascotaRepo, times(1)).findById(1L);
        verify(mascotaRepo, times(1)).save(mascota);
        verify(mascotaMapper, times(1)).updateEntity(mascotaDTO, mascota);
        verify(mascotaMapper, times(1)).ToDTO(mascota);
    }

    @Test
    void testActualizarMascotaThrowsException() {
        // Configuración del mock para cuando no se encuentra la mascota
        when(mascotaRepo.findById(1L)).thenReturn(java.util.Optional.empty());

        // Llamada al método y verificación de la excepción
        MascotaNotFoundException exception = assertThrows(MascotaNotFoundException.class, () -> {
            mascotaService.ActualizarMascota(1L, mascotaDTO);
        });

        assertEquals("La actualización no pudo ser completada porque la mascota no fue encontrada con ID 1", exception.getMessage());
    }


    //------------ metodo Eliminar mascota ---------------
    @Test
    void testEliminarMascota() {
        // Configuración de los mocks
        when(mascotaRepo.existsById(1L)).thenReturn(true); // La mascota existe
        doNothing().when(mascotaRepo).deleteById(1L); // No hace nada cuando elimina

        // Llamada al método
        mascotaService.eliminarMascota(1L);

        // Verificaciones
        verify(mascotaRepo, times(1)).existsById(1L);
        verify(mascotaRepo, times(1)).deleteById(1L);
    }

    @Test
    void testEliminarMascotaThrowsException() {
        // Configuración del mock para cuando no existe la mascota
        when(mascotaRepo.existsById(1L)).thenReturn(false);

        // Llamada al método y verificación de la excepción
        MascotaNotFoundException exception = assertThrows(MascotaNotFoundException.class, () -> {
            mascotaService.eliminarMascota(1L);
        });

        assertEquals("Mascota no pudo ser eliminada, no se encontró ninguna coincidencia con el ID [ 1 ] proporcionado", exception.getMessage());
    }


    // ------- obtener mascota por Especie ---------------
    @Test
    void testObtenerMascotasPorEspecie() {
        // Configuración del mock
        when(mascotaRepo.findByEspecie(Especie.PERRO)).thenReturn(List.of(mascota)); // Mascotas de tipo PERRO
        when(mascotaMapper.ToDTO(mascota)).thenReturn(mascotaDTO); // Mapeo a DTO

        // Llamada al método
        List<MascotaDTO> result = mascotaService.obtenerMascotasPorEspecie(Especie.PERRO);

        // Verificaciones
        assertNotNull(result);
        assertEquals(1, result.size()); // Verifica que la lista tiene 1 elemento
        assertEquals("Firulais", result.get(0).getNombre()); // Verifica el nombre

        // Verificación de las interacciones
        verify(mascotaRepo, times(1)).findByEspecie(Especie.PERRO);
        verify(mascotaMapper, times(1)).ToDTO(mascota);
    }

    @Test
    void testObtenerMascotasPorEspecieThrowsException() {
        // Configuración para que la consulta falle
        when(mascotaRepo.findByEspecie(Especie.PERRO)).thenThrow(new RuntimeException("Error al obtener las mascotas por especie"));

        // Llamada al método y verificación de la excepción
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            mascotaService.obtenerMascotasPorEspecie(Especie.PERRO);
        });

        assertEquals("Error al obtener las mascotas por especie", exception.getMessage());
    }


}

