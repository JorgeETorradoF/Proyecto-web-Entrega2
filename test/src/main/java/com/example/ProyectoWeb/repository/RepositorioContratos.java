package com.example.ProyectoWeb.repository;

import org.springframework.stereotype.Repository;

import com.example.ProyectoWeb.model.Contratos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

@Repository
public interface RepositorioContratos extends CrudRepository<Contratos, Integer> {
    @Query("SELECT c FROM Contratos c WHERE c.idArrendatario = :id")
    Iterable<Contratos> getAllByIdArrendatario(@Param("id") int id);

    @Query("SELECT COUNT(c) > 0 FROM Contratos c WHERE (c.fechaInicio = :fechaInicio AND c.fechaFinal = :fechaFin) OR (c.fechaInicio < :fechaFin AND c.fechaFinal > :fechaInicio)")
    boolean hayConflictoHorarios(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
}
