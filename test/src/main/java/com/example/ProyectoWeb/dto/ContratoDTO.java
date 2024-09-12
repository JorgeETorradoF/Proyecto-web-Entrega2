package com.example.ProyectoWeb.dto;

import com.example.ProyectoWeb.model.ContratoTemplate;

import java.time.LocalDateTime;

public class ContratoDTO extends ContratoTemplate {
    public ContratoDTO(float precio,LocalDateTime fechaInicio,LocalDateTime fechaFinal)
    {
        super(precio,fechaInicio,fechaFinal);
    }
}
