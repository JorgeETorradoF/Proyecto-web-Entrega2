package com.example.ProyectoWeb.repository;

import org.springframework.stereotype.Repository;

import com.example.ProyectoWeb.model.Contratos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface RepositorioContratos extends CrudRepository<Contratos, Integer> {
    @Query("SELECT c FROM Contratos c WHERE c.idArrendatario = :id")
    Iterable<Contratos> getAllByIdArrendatario(@Param("id") int id);
    
}
