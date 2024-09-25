package com.example.ProyectoWeb.model;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    
    protected String nombre;
    protected String apellido;
    protected String contraseña;
    protected String correo;

    @Column(name = "promedio")
    protected Float promedio = 0.0f;

    @Column(name = "canti_calif")
    protected Integer cantiCalif;
}
