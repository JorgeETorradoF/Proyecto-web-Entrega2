package com.example.ProyectoWeb.entrega1.dto;

import com.example.ProyectoWeb.entrega1.model.ContratoTemplate;

import java.time.LocalDateTime;

public class ContratoDTO extends ContratoTemplate {
    public ContratoDTO(float precio,LocalDateTime fechaInicio,LocalDateTime fechaFinal)
    {
        super(precio,fechaInicio,fechaFinal);
    }
}
