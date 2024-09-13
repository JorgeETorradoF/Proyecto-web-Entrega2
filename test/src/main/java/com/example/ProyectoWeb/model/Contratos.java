package com.example.ProyectoWeb.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Contratos extends ContratoTemplate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_propiedad")
    private int idPropiedad;
    @Column(name = "id_arrendatario")
    private int idArrendatario;
    //-1 es rechazado, 0 pendiente, 1 aceptado
    private int estado;
    private float precio;
}
