package com.example.ProyectoWeb.test;

import com.example.ProyectoWeb.dto.ContratoDTO;
import com.example.ProyectoWeb.exception.CamposInvalidosException;
import com.example.ProyectoWeb.exception.ConflictoHorariosException;
import com.example.ProyectoWeb.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.model.Contratos;
import com.example.ProyectoWeb.repository.RepositorioContratos;
import com.example.ProyectoWeb.repository.RepositorioPropiedades;
import com.example.ProyectoWeb.service.ServicioContratos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ServicioContratosTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RepositorioContratos repositorioContratos;

    @Mock
    private RepositorioPropiedades repositorioPropiedades;

    @InjectMocks
    private ServicioContratos servicioContratos;

    private ContratoDTO contratoDTO;
    private Contratos contrato;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializamos los objetos de prueba
        contratoDTO = new ContratoDTO(
            LocalDateTime.of(2023, 9, 14, 12, 0),
            LocalDateTime.of(2023, 9, 15, 12, 0)
        );

        contrato = new Contratos(
            1,
            1,
            1,
            0,
            120.5f,
            LocalDateTime.of(2023, 9, 14, 12, 0),
            LocalDateTime.of(2023, 9, 15, 12, 0)
        );
    }

    @Test
    void testCheckCamposContratoValid() {
        boolean result = servicioContratos.checkCamposContrato(contratoDTO);
        assertTrue(result);
    }

    @Test
    void testCheckCamposContratoInvalidDates() {
        ContratoDTO invalidContrato = new ContratoDTO(
            LocalDateTime.of(2023, 9, 15, 12, 0),
            LocalDateTime.of(2023, 9, 14, 12, 0)
        );
        boolean result = servicioContratos.checkCamposContrato(invalidContrato);
        assertFalse(result);
    }

    @Test
    void testSaveContratoSuccess() throws CamposInvalidosException, PropNoEncontradaException, ConflictoHorariosException {
        when(modelMapper.map(any(ContratoDTO.class), eq(Contratos.class))).thenReturn(contrato);
        when(repositorioContratos.hayConflictoHorarios(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(false);
        when(repositorioPropiedades.findPrecioById(eq(1))).thenReturn(Optional.of(100.0f));
        when(repositorioContratos.save(any(Contratos.class))).thenReturn(contrato);

        Contratos savedContrato = servicioContratos.saveContrato(contratoDTO, 1, 1);

        assertNotNull(savedContrato);
        assertEquals(100.0f, savedContrato.getPrecio());
    }

    @Test
    void testSaveContratoConflictoHorarios() throws CamposInvalidosException, PropNoEncontradaException {
        when(modelMapper.map(any(ContratoDTO.class), eq(Contratos.class))).thenReturn(contrato);
        when(repositorioContratos.hayConflictoHorarios(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);

        assertThrows(ConflictoHorariosException.class, () -> {
            servicioContratos.saveContrato(contratoDTO, 1, 1);
        });
    }

    @Test
    void testSaveContratoPropNoEncontrada() throws CamposInvalidosException, ConflictoHorariosException {
        when(modelMapper.map(any(ContratoDTO.class), eq(Contratos.class))).thenReturn(contrato);
        when(repositorioContratos.hayConflictoHorarios(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(false);
        when(repositorioPropiedades.findPrecioById(eq(1))).thenReturn(Optional.empty());

        assertThrows(PropNoEncontradaException.class, () -> {
            servicioContratos.saveContrato(contratoDTO, 1, 1);
        });
    }

    @Test
    void testSaveContratoCamposInvalidos() {
        ContratoDTO invalidContrato = new ContratoDTO(
            LocalDateTime.of(2023, 9, 14, 12, 0),
            LocalDateTime.of(2023, 9, 14, 12, 0)
        );

        assertThrows(CamposInvalidosException.class, () -> {
            servicioContratos.saveContrato(invalidContrato, 1, 1);
        });
    }

    @Test
    void testGetContratosArrendatario() {
        Iterable<Contratos> contratos = List.of(contrato);
        when(repositorioContratos.getAllByIdArrendatario(eq(1))).thenReturn(contratos);

        Iterable<Contratos> result = servicioContratos.getContratosArrendatario(1);

        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        assertEquals(contrato, result.iterator().next());
    }

    @Test
    void testGetContratosArrendador() {
        Iterable<Contratos> contratos = List.of(contrato);
        when(repositorioContratos.getAllByIdArrendador(eq(1))).thenReturn(contratos);

        Iterable<Contratos> result = servicioContratos.getContratosArrendador(1);

        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        assertEquals(contrato, result.iterator().next());
    }
}
