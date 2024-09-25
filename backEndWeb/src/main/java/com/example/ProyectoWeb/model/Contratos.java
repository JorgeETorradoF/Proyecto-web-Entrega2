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

import java.time.LocalDateTime;

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
    
    public Contratos(int id, int idProp, int idArrendatario, int estado, float precio, LocalDateTime fechaInicio, LocalDateTime fechaFinal)
    {
        super(fechaInicio, fechaFinal);
        this.id = id;
        this.idArrendatario = idArrendatario;
        this.idPropiedad = idProp;
        this.estado = estado;
        this.precio = precio;
    }
}
