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

    private String estado;  
    private String urlImagen; 

    public PropiedadDTO(int idArrendador, String nombrePropiedad, String departamento, String municipio, 
        String tipoIngreso, String descripcion, int cantidadHabitaciones, int cantidadBanos, 
        Boolean permiteMascotas, Boolean tienePiscina, Boolean tieneAsador, Float valorNoche, 
        String estado, String urlImagen) 
    {
        super(idArrendador, nombrePropiedad, departamento, municipio, tipoIngreso, descripcion, 
        cantidadHabitaciones, cantidadBanos, 
        permiteMascotas != null ? permiteMascotas : false, 
        tienePiscina != null ? tienePiscina : false, 
        tieneAsador != null ? tieneAsador : false, 
        valorNoche != null ? valorNoche : 0.0f); 

        this.estado = estado != null ? estado : "activo"; 
        this.urlImagen = urlImagen;
    }
}
