package com.example.ProyectoWeb.service;
import com.example.ProyectoWeb.model.Arrendadores;
import com.example.ProyectoWeb.model.Arrendatarios;
import com.example.ProyectoWeb.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.repository.RepositorioArrendatarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioUsuarios {
    @Autowired
    private RepositorioArrendadores repositorioArrendadores;
    @Autowired
    private RepositorioArrendatarios repositorioArrendatarios;

    // Regresa un iterable que contiene todos los arrendadores existentes
    public Iterable<Arrendadores> getAllArrendadores() {
        return repositorioArrendadores.findAll();
    }
    // Regresa un iterable que contiene todos los arrendatarios existentes
    public Iterable<Arrendatarios> getAllArrendatarios() {
        return repositorioArrendatarios.findAll();
    }
    
}
