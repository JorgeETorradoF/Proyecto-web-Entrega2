package com.example.ProyectoWeb.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ProyectoWeb.model.Propiedades;
import com.example.ProyectoWeb.service.ServicioPropiedad;

@Controller
@RequestMapping("/api")
public class ControladorGetAll {

    @Autowired
    private ServicioPropiedad servicioPropiedades;  // Se asume que existe un servicio llamado ServicioPropiedades

    @GetMapping("/get-propiedades")
    public @ResponseBody Iterable<Propiedades> getAllPropiedades() {
        return servicioPropiedades.getAllPropiedades();  // Se asume que este método existe en el servicio
    }
}

