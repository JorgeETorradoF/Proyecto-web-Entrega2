package com.example.ProyectoWeb.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.ProyectoWeb.model.Propiedades;

@Repository
public interface RepositorioPropiedades extends CrudRepository<Propiedades, Integer> {
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Propiedades p WHERE p.idArrendador = :idArrendador AND p.nombrePropiedad = :nombre")
    boolean propiedadDitto(@Param("idArrendador") int idArrendador, @Param("nombre") String nombre);
    
    @Query("SELECT p FROM Propiedades p WHERE p.idArrendador = :id")
    Iterable<Propiedades> getAllById(@Param("id") int id);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Propiedades p WHERE p.id = :idPropiedad AND p.idArrendador = :idUsuario")
    boolean propiedadPertenece(@Param("idUsuario") int idUsuario,@Param("idPropiedad") int idPropiedad);

    @Query("SELECT p FROM Propiedades p WHERE p.id = :id")
    Optional<Propiedades> findById(@Param("id") int id);

    @Query("SELECT p.valorNoche FROM Propiedades p WHERE p.id = :id")
    Optional<Float> findPrecioById(@Param("id") int id);
}
