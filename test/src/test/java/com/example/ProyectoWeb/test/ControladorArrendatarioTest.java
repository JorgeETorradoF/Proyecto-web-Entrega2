package com.example.ProyectoWeb.test;

import com.example.ProyectoWeb.dto.ContratoDTO;
import com.example.ProyectoWeb.exception.CamposInvalidosException;
import com.example.ProyectoWeb.exception.ConflictoHorariosException;
import com.example.ProyectoWeb.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.model.Contratos;
import com.example.ProyectoWeb.prueba.controllers.ControladorArrendatario;
import com.example.ProyectoWeb.service.ServicioContratos;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ControladorArrendatarioTest {

    @Mock
    private ServicioContratos servicioContratos;

    @InjectMocks
    private ControladorArrendatario controladorArrendatario;

    @Mock
    private Model model;

    private ContratoDTO contratoDTO;
    private Contratos contratoRet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializamos los objetos necesarios con base en el JSON
        contratoDTO = new ContratoDTO(
            LocalDateTime.of(2023, 9, 14, 12, 0),
            LocalDateTime.of(2023, 9, 15, 12, 0)
        );

        contratoRet = new Contratos(
            1, // ID del contrato
            1, // ID del arrendatario
            1, // ID de la propiedad
            0,
            0.0f,
            LocalDateTime.of(2023, 9, 14, 12, 0),
            LocalDateTime.of(2023, 9, 15, 12, 0)
        );
    }

    @Test
    void testSolicitarArriendo() throws CamposInvalidosException, PropNoEncontradaException, ConflictoHorariosException {
        int id = 1;
        int idPropiedad = 1;

        when(servicioContratos.saveContrato(any(ContratoDTO.class), eq(id), eq(idPropiedad))).thenReturn(contratoRet);

        ResponseEntity<?> response = controladorArrendatario.solicitarArriendo(id, idPropiedad, model, contratoDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contratoRet, response.getBody());
    }

    @Test
    void testSolicitarArriendoException() throws CamposInvalidosException, PropNoEncontradaException, ConflictoHorariosException {
        int id = 1;
        int idPropiedad = 1;

        when(servicioContratos.saveContrato(any(ContratoDTO.class), eq(id), eq(idPropiedad)))
                .thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = controladorArrendatario.solicitarArriendo(id, idPropiedad, model, contratoDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error al solicitar el arriendo: Error", response.getBody());
    }

    @Test
    void testGetContratos() {
        int id = 1;
        Iterable<Contratos> contratos = List.of(contratoRet);

        when(servicioContratos.getContratosArrendatario(eq(id))).thenReturn(contratos);

        Iterable<Contratos> response = controladorArrendatario.getContratos(id, model);

        assertEquals(contratos, response);
    }
}
