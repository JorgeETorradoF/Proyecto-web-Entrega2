package com.example.ProyectoWeb.prueba.controllers;

import com.example.ProyectoWeb.service.ServicioCalificacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calificaciones")
public class ControladorCalificacion {

    @Autowired
    private ServicioCalificacion servicioCalificacion;

    @PostMapping("/arrendador/{id}")
    public ResponseEntity<?> calificarArrendador(
        @PathVariable Integer id,
        @RequestParam Double calificacion) {
    
    try {
        return ResponseEntity.ok(servicioCalificacion.calificarArrendador(id, calificacion));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}


    @PostMapping("/arrendatario/{id}")
    public ResponseEntity<?> calificarArrendatario(
            @PathVariable Integer id,
            @RequestParam Float calificacion) {  
        
        try {
            return ResponseEntity.ok(servicioCalificacion.calificarArrendatario(id, calificacion));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
