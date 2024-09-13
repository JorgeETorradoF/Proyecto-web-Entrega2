package com.example.ProyectoWeb.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "arrendatarios")
@NoArgsConstructor
public class Arrendatarios extends Usuario {
    public Arrendatarios(int id, String nombre, String apellido, String contraseña, String correo, float promedio, int cantiCalif) {
        super(id, nombre, apellido, contraseña, correo, promedio, cantiCalif);
    }
}
