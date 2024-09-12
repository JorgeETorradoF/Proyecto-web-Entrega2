package com.example.ProyectoWeb.entrega1.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PropiedadTemplate {
    protected int idArrendador;
    protected String nombrePropiedad;
    protected String departamento;
    protected String municipio;
    protected String tipoIngreso;
    protected String descripcion;
    protected int cantidadHabitaciones;
    protected int cantidadBaños;
    protected boolean permiteMascotas;
    protected boolean tienePiscina;
    protected boolean tieneAsador;
    protected float valorNoche;
}
