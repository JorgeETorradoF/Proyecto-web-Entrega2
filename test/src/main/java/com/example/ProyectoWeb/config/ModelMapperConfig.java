package com.example.ProyectoWeb.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.ProyectoWeb.dto.PropiedadDTO;
import com.example.ProyectoWeb.model.Propiedades;
import com.example.ProyectoWeb.dto.ContratoDTO;
import com.example.ProyectoWeb.model.Contratos;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Configuración personalizada para ignorar el campo 'id' al mapear propiedad
        modelMapper.addMappings(new PropertyMap<PropiedadDTO, Propiedades>() {
            @Override
            protected void configure() {
                // Ignorar el campo 'id' en Propiedades cuando se mapea desde PropiedadDTO
                skip(destination.getId());
            }
        });

        // Configuración personalizada para ignorar los campos id al mapear contrato
        modelMapper.addMappings(new PropertyMap<ContratoDTO, Contratos>() {
            @Override
            protected void configure() {
                // Mapear cada campo explícitamente
                map(source.getPrecio(), destination.getPrecio());
                map(source.getFechaInicio(), destination.getFechaInicio());
                map(source.getFechaFinal(), destination.getFechaFinal());

                // Ignorar los campos de id que se gestionarán después
                skip(destination.getId());
                skip(destination.getIdArrendatario());
                skip(destination.getIdPropiedad());
                skip(destination.getEstado());
                skip(destination.isEnConflicto());
            }
        });

        return modelMapper;
    }
}
