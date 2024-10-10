package com.example.ProyectoWeb.dto;

import com.example.ProyectoWeb.model.PropiedadTemplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropiedadDTO extends PropiedadTemplate {

    private String estado;  // nuevo campo para manejar el estado de la propiedad (activo, inactivo)
    private String urlImagen; // nuevo campo para la URL de la imagen de la propiedad

    public PropiedadDTO(int idArrendador, String nombrePropiedad, String departamento, String municipio, 
        String tipoIngreso, String descripcion, int cantidadHabitaciones, int cantidadBaños, 
        boolean permiteMascotas, boolean tienePiscina, boolean tieneAsador, float valorNoche,
        String estado, String urlImagen) 
    {
        super(idArrendador, nombrePropiedad, departamento, municipio, tipoIngreso, descripcion, 
        cantidadHabitaciones, cantidadBaños, permiteMascotas, tienePiscina, tieneAsador, valorNoche);    

        this.estado = estado;
        this.urlImagen = urlImagen;
    }
}
