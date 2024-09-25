package com.example.ProyectoWeb.test;
import com.example.ProyectoWeb.dto.PropiedadDTO;
import com.example.ProyectoWeb.exception.CamposInvalidosException;
import com.example.ProyectoWeb.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.exception.PropRegistradaException;
import com.example.ProyectoWeb.model.Contratos;
import com.example.ProyectoWeb.model.Propiedades;
import com.example.ProyectoWeb.prueba.controllers.ControladorArrendador;
import com.example.ProyectoWeb.service.ServicioContratos;
import com.example.ProyectoWeb.service.ServicioPropiedad;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


class ControladorArrendadorTest {

    @Mock
    private ServicioPropiedad servicioPropiedad;
    @Mock
    private ServicioContratos servicioContratos;

    @InjectMocks
    private ControladorArrendador controladorArrendador;

    @Mock
    private Model model;

    private PropiedadDTO propiedadDTO;
    private Propiedades propRet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializar los objetos con valores específicos
        propiedadDTO = new PropiedadDTO(
            1,
            "casa del shitpost",
            "A",
            "Medellín",
            "Alquiler",
            "Hermosa casa con vista al lago y amplios espacios.",
            3,
            2,
            true,
            false,
            true,
            120.50f
        );

        propRet = new Propiedades(
            1,
            1,
            "casa del shitpost",
            "A",
            "Medellín",
            "Alquiler",
            "Hermosa casa con vista al lago y amplios espacios.",
            3,
            2,
            true,
            false,
            true,
            120.50f
        );
    }

    @Test
    void testRegistrarPropiedad() throws PropRegistradaException, CamposInvalidosException {
        int id = 1;
        propiedadDTO.setIdArrendador(id);

        when(servicioPropiedad.savePropiedad(any(PropiedadDTO.class))).thenReturn(propRet);

        ResponseEntity<?> response = controladorArrendador.registrarPropiedad(id, model, propiedadDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(propRet, response.getBody());
    }

    @Test
    void testModificarPropiedad() throws PropNoEncontradaException, CamposInvalidosException {
        int id = 1;
        int propId = 10;
        propiedadDTO.setIdArrendador(id);

        when(servicioPropiedad.modifyPropiedad(any(PropiedadDTO.class), eq(propId))).thenReturn(propRet);

        ResponseEntity<?> response = controladorArrendador.modificarPropiedad(id, propId, model, propiedadDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(propRet, response.getBody());
    }

    @Test
    void testRegistrarPropiedadException() throws PropRegistradaException, CamposInvalidosException {
        int id = 1;
        propiedadDTO.setIdArrendador(id);

        when(servicioPropiedad.savePropiedad(any(PropiedadDTO.class))).thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = controladorArrendador.registrarPropiedad(id, model, propiedadDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error al registrar la propiedad: Error", response.getBody());
    }

    @Test
    void testModificarPropiedadException() throws PropNoEncontradaException, CamposInvalidosException {
        int id = 1;
        int propId = 10;
        propiedadDTO.setIdArrendador(id);

        when(servicioPropiedad.modifyPropiedad(any(PropiedadDTO.class), eq(propId))).thenThrow(new RuntimeException("Error"));

        ResponseEntity<?> response = controladorArrendador.modificarPropiedad(id, propId, model, propiedadDTO);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error al modificar la propiedad: Error", response.getBody());
    }
    @Test
    void testMostrarDetallePropiedad() throws PropNoEncontradaException {
        int id = 1;
        int propiedadId = 1;

        when(servicioPropiedad.getPropiedad(eq(propiedadId), eq(id))).thenReturn(propRet);

        ResponseEntity<?> response = controladorArrendador.mostrarDetallePropiedad(id, propiedadId, model);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(propRet, response.getBody());
    }

    @Test
    void testMostrarDetallePropiedadException() throws PropNoEncontradaException {
        int id = 1;
        int propiedadId = 100;

        when(servicioPropiedad.getPropiedad(eq(propiedadId), eq(id))).thenThrow(new PropNoEncontradaException("No encontrada"));

        ResponseEntity<?> response = controladorArrendador.mostrarDetallePropiedad(id, propiedadId, model);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error al obtener la propiedad: No encontrada", response.getBody());
    }
    @Test
    void testGetAllProperties() {
        int id = 1;

        Iterable<Propiedades> propiedades = List.of(propRet);

        when(servicioPropiedad.getPropiedades(eq(id))).thenReturn(propiedades);

        Iterable<Propiedades> response = controladorArrendador.getAllProperties(id, model);

        assertEquals(propiedades, response);
    }
    @Test
    void testGetContratos() {
        int id = 1;
        Iterable<Contratos> contratos = List.of(new Contratos());

        when(servicioContratos.getContratosArrendador(eq(id))).thenReturn(contratos);

        Iterable<Contratos> response = controladorArrendador.getContratos(id, model);

        assertEquals(contratos, response);
}


}