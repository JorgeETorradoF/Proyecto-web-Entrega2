package com.example.ProyectoWeb.prueba.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.example.ProyectoWeb.dto.PropiedadDTO;
import com.example.ProyectoWeb.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.exception.ContratoNoExistenteException;
import com.example.ProyectoWeb.model.Contratos;
import com.example.ProyectoWeb.model.Propiedades;
import com.example.ProyectoWeb.service.ServicioContratos;
import com.example.ProyectoWeb.service.ServicioPropiedad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/api/arrendador/{id}")
public class ControladorArrendador {

    private static final Logger logger = LoggerFactory.getLogger(ControladorArrendador.class);

    private final ServicioPropiedad servicioPropiedad;
    private final ServicioContratos servicioContratos;

    // Directorio raíz para guardar imágenes
    private static final String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/uploads/";

    public ControladorArrendador(ServicioPropiedad servicioPropiedad, ServicioContratos servicioContratos) {
        this.servicioPropiedad = servicioPropiedad;
        this.servicioContratos = servicioContratos;
    }

    // Método ajustado para recibir la propiedad junto con la imagen
    @PostMapping(value = "/registrar-propiedad", consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<?> registrarPropiedad(
            @PathVariable("id") int id,
            @RequestPart("propiedadDTO") PropiedadDTO propiedadDTO,
            @RequestPart("imagen") MultipartFile imagen,
            Model model) {

        logger.info("Iniciando registro de propiedad para el arrendador con ID: {}", id);
        model.addAttribute("id", id);
        propiedadDTO.setIdArrendador(id);
        propiedadDTO.setEstado("activo");  // La propiedad siempre inicia como activa

        try {
            // Verifica si la imagen no está vacía
            if (!imagen.isEmpty()) {
                logger.info("Procesando imagen para la propiedad: {}", propiedadDTO.getNombrePropiedad());
                // Define la ruta para guardar la imagen
                String directorioArrendador = UPLOAD_DIRECTORY + "arrendador_" + id;
                File directorio = new File(directorioArrendador);

                // Crea el directorio si no existe
                if (!directorio.exists()) {
                    logger.info("Creando directorio para el arrendador: {}", directorioArrendador);
                    directorio.mkdirs();
                }

                // Genera un nombre único para la imagen
                String nombreImagen = System.currentTimeMillis() + "_" + imagen.getOriginalFilename();

                // Define la ruta completa de la imagen
                Path rutaImagen = Paths.get(directorioArrendador, nombreImagen);

                // Guarda la imagen en la ruta especificada
                Files.copy(imagen.getInputStream(), rutaImagen, StandardCopyOption.REPLACE_EXISTING);

                // Establece la URL de la imagen en el DTO de la propiedad
                propiedadDTO.setUrlImagen("/api/arrendador/" + id + "/imagenes/" + nombreImagen);
                logger.info("Imagen guardada exitosamente en la ruta: {}", rutaImagen);
            }

            // Guardar la propiedad
            logger.info("Guardando propiedad para el arrendador con ID: {}", id);
            return ResponseEntity.ok(servicioPropiedad.savePropiedad(propiedadDTO));
        } catch (IOException e) {
            logger.error("Error al guardar la imagen: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error al registrar la propiedad: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la propiedad: " + e.getMessage());
        }
    }

    // Método para servir imágenes guardadas
    @GetMapping("/imagenes/{nombreImagen}")
    @ResponseBody
    public ResponseEntity<byte[]> obtenerImagen(@PathVariable("id") int id, @PathVariable("nombreImagen") String nombreImagen) {
        logger.info("Buscando imagen: {} para el arrendador con ID: {}", nombreImagen, id);
        try {
            Path rutaImagen = Paths.get(UPLOAD_DIRECTORY + "arrendador_" + id, nombreImagen);
            byte[] imagen = Files.readAllBytes(rutaImagen);
            logger.info("Imagen encontrada: {}", rutaImagen);
            return ResponseEntity.ok(imagen);
        } catch (IOException e) {
            logger.error("Error al obtener la imagen: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Obtener todas las propiedades de un arrendador
    @GetMapping("/propiedades")
    public @ResponseBody Iterable<Propiedades> getAllProperties(@PathVariable("id") int id, Model model) {
        logger.info("Obteniendo todas las propiedades del arrendador con ID: {}", id);
        try {
            model.addAttribute("id", id);
            return servicioPropiedad.getPropiedades(id);
        } catch (Exception e) {
            logger.error("Error al obtener propiedades: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error: ", e);
        }
    }

    // Modificar propiedad existente
    @PutMapping("/modificar-propiedad/{propId}")
    public ResponseEntity<?> modificarPropiedad(
            @PathVariable("id") int id,
            @PathVariable("propId") int propId,
            @RequestBody PropiedadDTO propiedadDTO,
            Model model) {

        logger.info("Modificando propiedad con ID: {} para el arrendador con ID: {}", propId, id);
        model.addAttribute("id", id);
        model.addAttribute("propId", propId);
        propiedadDTO.setIdArrendador(id);

        try {
            return ResponseEntity.ok(servicioPropiedad.modifyPropiedad(propiedadDTO, propId));
        } catch (Exception e) {
            logger.error("Error al modificar la propiedad: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar la propiedad: " + e.getMessage());
        }
    }

    // Mostrar detalle de una propiedad
    @GetMapping("/propiedad/{propiedadId}")
    public ResponseEntity<?> mostrarDetallePropiedad(@PathVariable("id") int id, @PathVariable("propiedadId") int propiedadId, Model model) {
        logger.info("Obteniendo detalle de la propiedad con ID: {} para el arrendador con ID: {}", propiedadId, id);
        try {
            return ResponseEntity.ok(servicioPropiedad.getPropiedad(propiedadId, id));
        } catch (PropNoEncontradaException e) {
            logger.error("Error al obtener la propiedad: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener la propiedad: " + e.getMessage());
        }
    }

    // Obtener contratos del arrendador
    @GetMapping("/mis-contratos")
    public @ResponseBody Iterable<Contratos> getContratos(@PathVariable("id") int id, Model model) {
        logger.info("Obteniendo contratos del arrendador con ID: {}", id);
        model.addAttribute("id", id);
        return servicioContratos.getContratosArrendador(id);
    }

    // Aceptar contrato
    @PutMapping("/aceptar-contrato/{contratoId}")
    public ResponseEntity<?> aceptarContrato(@PathVariable("id") int id, @PathVariable("contratoId") int contratoId) {
        logger.info("Aceptando contrato con ID: {} para el arrendador con ID: {}", contratoId, id);
        try {
            Contratos contratoAceptado = servicioContratos.aceptarContrato(contratoId);
            return ResponseEntity.ok(contratoAceptado);
        } catch (ContratoNoExistenteException e) {
            logger.error("Error al aceptar contrato: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al aceptar el contrato: " + e.getMessage());
        }
    }

    // Rechazar contrato
    @PutMapping("/rechazar-contrato/{contratoId}")
    public ResponseEntity<?> rechazarContrato(@PathVariable("id") int id, @PathVariable("contratoId") int contratoId) {
        logger.info("Rechazando contrato con ID: {} para el arrendador con ID: {}", contratoId, id);
        try {
            Contratos contratoRechazado = servicioContratos.rechazarContrato(contratoId);
            return ResponseEntity.ok(contratoRechazado);
        } catch (ContratoNoExistenteException e) {
            logger.error("Error al rechazar contrato: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al rechazar el contrato: " + e.getMessage());
        }
    }
}
