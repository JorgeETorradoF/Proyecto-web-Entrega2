package com.example.ProyectoWeb.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
public class ContratoTemplate {
    @Column(name = "fecha_inicio", columnDefinition = "TIMESTAMP")
    protected LocalDateTime fechaInicio;
    @Column(name = "fecha_final", columnDefinition = "TIMESTAMP")
    protected LocalDateTime fechaFinal;
    
}
