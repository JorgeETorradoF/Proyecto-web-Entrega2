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
    @Column(name = "en_conflicto")
    private boolean enConflicto;
    public Contratos(int id,int idPropiedad,int idArrendatario,float precio,LocalDateTime fechaInicio,LocalDateTime fechaFinal,int estado,boolean enConflicto)
    {
        super(precio,fechaInicio,fechaFinal);
        this.id = id;
        this.idPropiedad = idPropiedad;
        this.idArrendatario = idArrendatario;
        this.estado = estado;
        this.enConflicto = enConflicto;
    }
}
