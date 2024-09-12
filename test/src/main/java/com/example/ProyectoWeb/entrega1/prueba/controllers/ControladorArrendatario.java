package com.example.ProyectoWeb.entrega1.prueba.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ProyectoWeb.entrega1.dto.ContratoDTO;
import com.example.ProyectoWeb.entrega1.model.Contratos;
import com.example.ProyectoWeb.entrega1.service.ServicioContratos;

@Controller
@RequestMapping("/arrendatario/{id}")
public class ControladorArrendatario {

    private final ServicioContratos servicioContratos;

    public ControladorArrendatario(ServicioContratos servicioContratos)
    {
        this.servicioContratos = servicioContratos;
    }

    @PostMapping(value = "/solicitar-arriendo/{idPropiedad}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> solicitarArriendo(@PathVariable("id") int id, @PathVariable("idPropiedad") int idPropiedad, Model model,@RequestBody ContratoDTO contratoDTO)
    {
        model.addAttribute("id", id);
        model.addAttribute("idPropiedad", idPropiedad);
         try {
            // se guarda
            return ResponseEntity.ok(servicioContratos.saveContrato(contratoDTO, id, idPropiedad));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al solicitar el arriendo: " + e.getMessage());
        }
    }
    @GetMapping("/mis-contratos")
    public Iterable<Contratos> getContratos(@PathVariable("id") int id,Model model)
    {
        model.addAttribute("id", id);
        return servicioContratos.getContratosArrendatario(id);

    }
}
