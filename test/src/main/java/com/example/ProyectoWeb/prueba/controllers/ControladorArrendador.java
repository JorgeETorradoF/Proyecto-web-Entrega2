package com.example.ProyectoWeb.prueba.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import com.example.ProyectoWeb.dto.PropiedadDTO;
import com.example.ProyectoWeb.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.model.Contratos;
import com.example.ProyectoWeb.model.Propiedades;
import com.example.ProyectoWeb.service.ServicioContratos;
import com.example.ProyectoWeb.service.ServicioPropiedad;


@Controller
@RequestMapping("/arrendador/{id}")
public class ControladorArrendador {

    private final ServicioPropiedad servicioPropiedad;
    private final ServicioContratos servicioContratos;

    public ControladorArrendador(ServicioPropiedad servicioPropiedad, ServicioContratos servicioContratos)
    {
        this.servicioPropiedad = servicioPropiedad;
        this.servicioContratos = servicioContratos;
    }

    @PostMapping(value = "/registrar-propiedad", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> registrarPropiedad(@PathVariable("id") int id, Model model,@RequestBody PropiedadDTO propiedadDTO) {
        model.addAttribute("id", id);
        propiedadDTO.setIdArrendador(id);
        try {
            // se guarda
            return ResponseEntity.ok(servicioPropiedad.savePropiedad(propiedadDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar la propiedad: " + e.getMessage());
        }
    }

    @GetMapping("/propiedades")
    public @ResponseBody Iterable<Propiedades> getAllProperties(@PathVariable("id") int id, Model model) {
        try {
            model.addAttribute("id", id); 
            // retornamos iterable de propiedades
            return servicioPropiedad.getPropiedades(id);
        } catch (Exception e) {
            // Handle any exceptions or errors
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error: ", e);
        }
    }

    @PutMapping("/modificar-propiedad/{propId}")
    public ResponseEntity<?> modificarPropiedad(@PathVariable("id") int id, @PathVariable("propId") int propId,Model model,@RequestBody PropiedadDTO propiedadDTO) {
        model.addAttribute("id", id);
        model.addAttribute("propId", propId);
        propiedadDTO.setIdArrendador(id);
        try {
            // se guarda
            return ResponseEntity.ok(servicioPropiedad.modifyPropiedad(propiedadDTO, propId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al modificar la propiedad: " + e.getMessage());
        }
    }

    @GetMapping("/propiedad/{propiedadId}")
    public ResponseEntity<?> mostrarDetallePropiedad(@PathVariable("id") int id, @PathVariable("propiedadId") int propiedadId, Model model) {
        try{
            return ResponseEntity.ok(servicioPropiedad.getPropiedad(propiedadId,id));
        }
        catch(PropNoEncontradaException e)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al obtener la propiedad: " + e.getMessage());
        }
    }
    @GetMapping("/mis-contratos")
    public @ResponseBody Iterable<Contratos> getContratos(@PathVariable("id") int id,Model model)
    {
        model.addAttribute("id", id);
        return servicioContratos.getContratosArrendador(id);

    }    

}

    