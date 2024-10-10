package com.example.ProyectoWeb.service;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoWeb.dto.PropiedadDTO;
import com.example.ProyectoWeb.exception.CamposInvalidosException;
import com.example.ProyectoWeb.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.exception.PropRegistradaException;
import com.example.ProyectoWeb.model.Propiedades;
import com.example.ProyectoWeb.repository.RepositorioPropiedades;

@Service
public class ServicioPropiedad {

    private static final String mensajeCamposInvalidos = "No se admiten campos vacíos, intente de nuevo";
    private static final String propNoEncontradaMsg = "No se encuentra la propiedad del usuario solicitada";

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RepositorioPropiedades repositorioPropiedades;

    // Comprueba que no haya campos vacíos en la propiedad
    public boolean checkCamposPropiedad(PropiedadDTO prop) {
        if (prop == null) {
            return false; // Caso donde no tenga absolutamente nada
        }

        if (prop.getIdArrendador() <= 0) return false;
        if (prop.getNombrePropiedad() == null || prop.getNombrePropiedad().isEmpty()) return false;
        if (prop.getDepartamento() == null || prop.getDepartamento().isEmpty()) return false;
        if (prop.getMunicipio() == null || prop.getMunicipio().isEmpty()) return false;
        if (prop.getTipoIngreso() == null || prop.getTipoIngreso().isEmpty()) return false;
        if (prop.getDescripcion() == null || prop.getDescripcion().isEmpty()) return false;
        if (prop.getCantidadHabitaciones() <= 0) return false;
        if (prop.getCantidadBaños() <= 0) return false;
        if (prop.getValorNoche() <= 0) return false;
        if (prop.getEstado() == null || prop.getEstado().isEmpty()) return false; // Valida que el estado no esté vacío
        if (prop.getUrlImagen() == null || prop.getUrlImagen().isEmpty()) return false; // Valida que la URL de la imagen no esté vacía

        return true;
    }

    // Guarda una propiedad
    public Propiedades savePropiedad(PropiedadDTO propiedadDTO) throws PropRegistradaException, CamposInvalidosException {
        // Verificamos que no haya campos vacíos
        if (checkCamposPropiedad(propiedadDTO)) {
            // Verifica si la propiedad ya está registrada
            boolean propiedadRegistrada = repositorioPropiedades.propiedadDitto(propiedadDTO.getIdArrendador(), propiedadDTO.getNombrePropiedad());
            if (propiedadRegistrada) {
                throw new PropRegistradaException("La propiedad ya fue registrada por usted");
            }

            // Mapear el DTO a una entidad
            Propiedades propiedades = modelMapper.map(propiedadDTO, Propiedades.class);
            // Guardar la propiedad
            return repositorioPropiedades.save(propiedades);
        } else {
            throw new CamposInvalidosException(mensajeCamposInvalidos);
        }
    }

    // Regresa un iterable que contiene todas las propiedades del arrendador
    public Iterable<Propiedades> getPropiedades(int id) {
        return repositorioPropiedades.getAllById(id);
    }

    // Regresa una propiedad específica de un arrendador
    public Propiedades getPropiedad(int propId, int id) throws PropNoEncontradaException {
        if (repositorioPropiedades.propiedadPertenece(id, propId)) {
            Optional<Propiedades> propRet = repositorioPropiedades.findById(propId);
            if (propRet.isPresent()) {
                // Si se encuentra la propiedad, la retornamos
                return propRet.get();
            } else {
                throw new PropNoEncontradaException(propNoEncontradaMsg);
            }
        } else {
            throw new PropNoEncontradaException(propNoEncontradaMsg);
        }
    }

    // Modifica una propiedad
    public Propiedades modifyPropiedad(PropiedadDTO propiedadDTO, int propId) throws PropNoEncontradaException, CamposInvalidosException {
        // Verificamos que no haya campos vacíos
        if (checkCamposPropiedad(propiedadDTO)) {
            // Verificamos que la propiedad a modificar efectivamente le pertenezca al arrendador
            boolean lePertenece = repositorioPropiedades.propiedadPertenece(propiedadDTO.getIdArrendador(), propId);
            if (lePertenece) {
                Propiedades propRetorno = modelMapper.map(propiedadDTO, Propiedades.class);
                propRetorno.setId(propId);
                return repositorioPropiedades.save(propRetorno);
            } else {
                throw new PropNoEncontradaException(propNoEncontradaMsg);
            }
        } else {
            throw new CamposInvalidosException(mensajeCamposInvalidos);
        }
    }

    // Desactiva una propiedad (cambia el estado a 'inactivo')
    public Propiedades desactivarPropiedad(int propId, int idArrendador) throws PropNoEncontradaException {
        if (repositorioPropiedades.propiedadPertenece(idArrendador, propId)) {
            Optional<Propiedades> propRet = repositorioPropiedades.findById(propId);
            if (propRet.isPresent()) {
                Propiedades propiedad = propRet.get();
                propiedad.setEstado("inactivo"); // Cambia el estado de la propiedad
                return repositorioPropiedades.save(propiedad);
            } else {
                throw new PropNoEncontradaException(propNoEncontradaMsg);
            }
        } else {
            throw new PropNoEncontradaException(propNoEncontradaMsg);
        }
    }
}
