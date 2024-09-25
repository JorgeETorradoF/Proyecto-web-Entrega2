package com.example.ProyectoWeb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "arrendadores")
@NoArgsConstructor
public class Arrendadores extends Usuario {

    public Arrendadores(int id, String nombre, String apellido, String contraseña, String correo, float promedio, int cantiCalif) {
        super(id, nombre, apellido, contraseña, correo, promedio, cantiCalif);
    }

}

