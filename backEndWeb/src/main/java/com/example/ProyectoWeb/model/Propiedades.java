package com.example.ProyectoWeb.model;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Propiedades extends PropiedadTemplate{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String estado; 
    private String urlImagen; 

    public Propiedades(int id, int idArrendador, String nombrePropiedad, String departamento, String municipio,
                       String tipoIngreso, String descripcion, int cantidadHabitaciones, int cantidadBanos, 
                       boolean permiteMascotas, boolean tienePiscina, boolean tieneAsador, float valorNoche,
                       String estado, String urlImagen) 
    {
        super(idArrendador, nombrePropiedad, departamento, municipio, tipoIngreso, descripcion, 
              cantidadHabitaciones, cantidadBanos, permiteMascotas, tienePiscina, tieneAsador, valorNoche);
        this.id = id;
        this.estado = estado; 
        this.urlImagen = urlImagen; 
    }
    
}

