package com.example.ProyectoWeb.test;
import com.example.ProyectoWeb.dto.RegistroDTO;
import com.example.ProyectoWeb.exception.CamposInvalidosException;
import com.example.ProyectoWeb.exception.CorreoRegistradoException;
import com.example.ProyectoWeb.model.Arrendadores;
import com.example.ProyectoWeb.model.Arrendatarios;
import com.example.ProyectoWeb.model.Usuario;
import com.example.ProyectoWeb.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.repository.RepositorioArrendatarios;
import com.example.ProyectoWeb.service.ServicioRegistro;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class ServicioRegistroTest {

    @Mock
    private RepositorioArrendadores repositorioArrendadores;

    @Mock
    private RepositorioArrendatarios repositorioArrendatarios;

    @InjectMocks
    private ServicioRegistro servicioRegistro;

    private RegistroDTO registroDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializamos los objetos de prueba
        registroDTO = new RegistroDTO(
            "Juan",
            "Pérez",
            "juan.perez@example.com",
            "password123",
            true // esArrendador
        );
    }

    @Test
    void testNombresApellidosValidos() {
        assertTrue(servicioRegistro.nombresApellidosValidos("Juan", "Pérez"));
        assertFalse(servicioRegistro.nombresApellidosValidos("", "Pérez"));
        assertFalse(servicioRegistro.nombresApellidosValidos("Juan", ""));
    }

    @Test
    void testContraseñaValida() {
        assertTrue(servicioRegistro.contraseñaValida("password123"));
        assertFalse(servicioRegistro.contraseñaValida(""));
    }

    @Test
    void testEmailValido() {
        assertTrue(servicioRegistro.emailValido("juan.perez@example.com"));
        assertFalse(servicioRegistro.emailValido("juan.perez@.com"));
        assertFalse(servicioRegistro.emailValido("juan.perez@example"));
    }

    @Test
    void testRegisterUserSuccessArrendador() throws CorreoRegistradoException, CamposInvalidosException {
        when(repositorioArrendadores.existsByCorreo(eq("juan.perez@example.com"))).thenReturn(false);
        when(repositorioArrendatarios.existsByCorreo(eq("juan.perez@example.com"))).thenReturn(false);

        // Crear una instancia de Arrendadores en lugar de Usuario
        Arrendadores arrendador = new Arrendadores(
            1, "Juan", "Pérez", "password123","juan.perez@example.com", 0.0f, 0
        );

        when(repositorioArrendadores.save(any(Arrendadores.class))).thenReturn(arrendador);

        Usuario savedUser = servicioRegistro.registerUser(registroDTO);

        assertNotNull(savedUser);
        assertEquals("juan.perez@example.com", savedUser.getCorreo());
        verify(repositorioArrendadores).save(any(Arrendadores.class));
    }

    @Test
    void testRegisterUserSuccessArrendatario() throws CorreoRegistradoException, CamposInvalidosException {
        registroDTO = new RegistroDTO(
            "Ana",
            "García",
            "ana.garcia@example.com",
            "password123",
            false // no es arrendador
        );

        when(repositorioArrendadores.existsByCorreo(eq("ana.garcia@example.com"))).thenReturn(false);
        when(repositorioArrendatarios.existsByCorreo(eq("ana.garcia@example.com"))).thenReturn(false);

        // Crear una instancia de Arrendatarios
        Arrendatarios arrendatario = new Arrendatarios(
            2, "Ana", "García", "password123", "ana.garcia@example.com",0.0f, 0
        );

        when(repositorioArrendatarios.save(any(Arrendatarios.class))).thenReturn(arrendatario);

        Usuario savedUser = servicioRegistro.registerUser(registroDTO);

        assertNotNull(savedUser);
        assertEquals("ana.garcia@example.com", savedUser.getCorreo());
        verify(repositorioArrendatarios).save(any(Arrendatarios.class));
    }

    @Test
    void testRegisterUserCorreoRegistrado() throws CamposInvalidosException {
        when(repositorioArrendadores.existsByCorreo(eq("juan.perez@example.com"))).thenReturn(true);
        when(repositorioArrendatarios.existsByCorreo(eq("juan.perez@example.com"))).thenReturn(false);

        assertThrows(CorreoRegistradoException.class, () -> {
            servicioRegistro.registerUser(registroDTO);
        });
    }

    @Test
    void testRegisterUserCamposInvalidos() {
        RegistroDTO invalidDTO = new RegistroDTO(
            "",
            "",
            "invalid-email",
            "",
            true
        );

        assertThrows(CamposInvalidosException.class, () -> {
            servicioRegistro.registerUser(invalidDTO);
        });
    }
}