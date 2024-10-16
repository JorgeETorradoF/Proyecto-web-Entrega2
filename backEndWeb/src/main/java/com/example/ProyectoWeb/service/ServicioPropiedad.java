package com.example.ProyectoWeb.service;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(ServicioPropiedad.class);


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RepositorioPropiedades repositorioPropiedades;

    // Comprueba que no haya campos vacíos en la propiedad
    public boolean checkCamposPropiedad(PropiedadDTO prop) {
        if (prop == null) {
            log.warn("El objeto PropiedadDTO es nulo.");
            return false; 
        }
    
       
        if (prop.getIdArrendador() <= 0) {
            log.warn("El idArrendador es inválido: {}", prop.getIdArrendador());
            return false;
        }
    
   
        if (prop.getNombrePropiedad() == null || prop.getNombrePropiedad().isEmpty()) {
            log.warn("El nombre de la propiedad es inválido: {}", prop.getNombrePropiedad());
            return false;
        }
    
     
        if (prop.getDepartamento() == null || prop.getDepartamento().isEmpty()) {
            log.warn("El departamento es inválido: {}", prop.getDepartamento());
            return false;
        }
    
        
        if (prop.getMunicipio() == null || prop.getMunicipio().isEmpty()) {
            log.warn("El municipio es inválido: {}", prop.getMunicipio());
            return false;
        }
    
       
        if (prop.getTipoIngreso() == null || prop.getTipoIngreso().isEmpty()) {
            log.warn("El tipo de ingreso es inválido: {}", prop.getTipoIngreso());
            return false;
        }
    
       
        if (prop.getDescripcion() == null || prop.getDescripcion().isEmpty()) {
            log.warn("La descripción es inválida: {}", prop.getDescripcion());
            return false;
        }
    
        if (prop.getCantidadHabitaciones() <= 0) {
            log.warn("La cantidad de habitaciones es inválida: {}", prop.getCantidadHabitaciones());
            return false;
        }
    
        if (prop.getCantidadBanos() <= 0) {
            log.warn("La cantidad de baños es inválida: {}", prop.getCantidadBanos());
            return false;
        }
    
        if (prop.getValorNoche() <= 0) {
            log.warn("El valor por noche es inválido: {}", prop.getValorNoche());
            return false;
        }
    
        if (prop.getEstado() == null || prop.getEstado().isEmpty()) {
            log.warn("El estado es inválido: {}", prop.getEstado());
            return false;
        }
    
        if (prop.getUrlImagen() == null || prop.getUrlImagen().isEmpty()) {
            log.warn("La URL de la imagen es inválida: {}", prop.getUrlImagen());
            return false;
        }
    
        log.info("Todos los campos de la propiedad son válidos.");
        return true;
    }
    

    public Propiedades savePropiedad(PropiedadDTO propiedadDTO) throws PropRegistradaException, CamposInvalidosException {
        log.info("Iniciando el proceso para guardar la propiedad del arrendador con ID: {}", propiedadDTO.getIdArrendador());
        log.info("PropiedadDTO: {}", propiedadDTO);  
        
        if (checkCamposPropiedad(propiedadDTO)) {
            boolean propiedadRegistrada = repositorioPropiedades.propiedadDitto(propiedadDTO.getIdArrendador(), propiedadDTO.getNombrePropiedad());
            
            if (propiedadRegistrada) {
                log.warn("La propiedad {} ya fue registrada para el arrendador con ID: {}", propiedadDTO.getNombrePropiedad(), propiedadDTO.getIdArrendador());
                throw new PropRegistradaException("La propiedad ya fue registrada por usted");
            }
    
            log.info("Mapeando PropiedadDTO a la entidad Propiedades");
            Propiedades propiedades = modelMapper.map(propiedadDTO, Propiedades.class);
    
            log.info("Guardando la propiedad en la base de datos: {}", propiedades);
    
            Propiedades propiedadGuardada = repositorioPropiedades.save(propiedades);
    
            // Log después de guardar la propiedad
            log.info("Propiedad guardada exitosamente: {}", propiedadGuardada);
            return propiedadGuardada;
            
        } else {
            log.error("Error: Se encontraron campos vacíos en el DTO de la propiedad: {}", propiedadDTO);
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
