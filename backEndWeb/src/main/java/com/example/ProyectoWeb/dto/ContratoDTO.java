package com.example.ProyectoWeb.dto;

import com.example.ProyectoWeb.model.ContratoTemplate;

import java.time.LocalDateTime;

public class ContratoDTO extends ContratoTemplate {
    public ContratoDTO(LocalDateTime fechaInicio,LocalDateTime fechaFinal)
    {
        super(fechaInicio,fechaFinal);
    }
}
