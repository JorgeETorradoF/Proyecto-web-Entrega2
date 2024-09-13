package com.example.ProyectoWeb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoWeb.dto.ContratoDTO;
import com.example.ProyectoWeb.exception.CamposInvalidosException;
import com.example.ProyectoWeb.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.model.Contratos;
import com.example.ProyectoWeb.repository.RepositorioContratos;
import com.example.ProyectoWeb.repository.RepositorioPropiedades;

import java.time.Duration;


@Service
public class ServicioContratos {

    private static final String mensajeCamposInvalidos = "No se admiten campos vacíos, intente de nuevo";

    private static final String propNoEncontradaMsg = "No se encuentra la propiedad del usuario solicitada";

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RepositorioContratos repositorioContratos;
    @Autowired
    private RepositorioPropiedades repositorioPropiedades;

    //Aquí se comprueba que no haya campos vacíos
    public boolean checkCamposContrato(ContratoDTO contrato) 
    {
        if (contrato == null) {
            return false; //caso donde no tenga absolutamente nada
        }

        // Validar que la fecha de inicio no sea luego de la fecha final
        if (contrato.getFechaInicio().isAfter(contrato.getFechaFinal())) {
            return false;
        }

        // Validar que las fechas no sean nulas
        if (contrato.getFechaInicio() == null || contrato.getFechaFinal() == null) {
            return false;
        }

        // Si llega hasta aquí pasó la inspección
        
        return true;
    }

    public Contratos saveContrato(ContratoDTO contratoDTO, int idArrendatario, int idPropiedad) throws CamposInvalidosException, PropNoEncontradaException
    {
        if(checkCamposContrato(contratoDTO))
        {
            Contratos nuevoCont = modelMapper.map(contratoDTO, Contratos.class);
            nuevoCont.setIdArrendatario(idArrendatario);
            nuevoCont.setIdPropiedad(idPropiedad);
            //ahora calculamos el precio
            float precioNoche = repositorioPropiedades.findPrecioById(idPropiedad).orElse(-1f);
            //si no hay precio, la propiedad es inválida o no existe
            if(precioNoche < 0)
            {
                throw new PropNoEncontradaException(propNoEncontradaMsg);
            }
            //si llega hasta aquí significa que la propiedad sí existe, por ende proseguimos
            long cantiNoches = Duration.between(nuevoCont.getFechaInicio(), nuevoCont.getFechaFinal()).toDays();
            nuevoCont.setPrecio(precioNoche*cantiNoches);
            //retornamos el resultado de guardar en el repo
            return repositorioContratos.save(nuevoCont);
        }
        else
        {
            throw new CamposInvalidosException(mensajeCamposInvalidos);
        }
    }
    public Iterable<Contratos> getContratosArrendatario(int idArrendatario)
    {
        return repositorioContratos.getAllByIdArrendatario(idArrendatario);
    }
    
}
