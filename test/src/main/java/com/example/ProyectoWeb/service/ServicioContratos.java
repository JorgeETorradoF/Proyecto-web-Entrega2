package com.example.ProyectoWeb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoWeb.dto.ContratoDTO;
import com.example.ProyectoWeb.exception.CamposInvalidosException;
import com.example.ProyectoWeb.exception.ConflictoHorariosException;
import com.example.ProyectoWeb.exception.PropNoEncontradaException;
import com.example.ProyectoWeb.model.Contratos;
import com.example.ProyectoWeb.repository.RepositorioContratos;
import com.example.ProyectoWeb.repository.RepositorioPropiedades;

import java.time.Duration;


@Service
public class ServicioContratos {

    private static final String mensajeCamposInvalidos = "No se admiten campos vacíos, intente de nuevo";

    private static final String propNoEncontradaMsg = "No se encuentra la propiedad solicitada";

    private static final String conflictoHorariosMsg = "La propiedad no está disponible en esos horarios, por favor intente con otro horario";
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

        // Validar que la fecha de inicio no sea mayor o igual a la fecha final
        if (contrato.getFechaInicio().isAfter(contrato.getFechaFinal()) || contrato.getFechaInicio().equals(contrato.getFechaFinal())) {
            return false;
        }
        // Validar que las fechas no sean nulas
        if (contrato.getFechaInicio() == null || contrato.getFechaFinal() == null) {
            return false;
        }

        // Si llega hasta aquí pasó la inspección
        
        return true;
    }

    public Contratos saveContrato(ContratoDTO contratoDTO, int idArrendatario, int idPropiedad) throws CamposInvalidosException, PropNoEncontradaException, ConflictoHorariosException
    {
        if(checkCamposContrato(contratoDTO))
        {
            Contratos nuevoCont = modelMapper.map(contratoDTO, Contratos.class);
            if(repositorioContratos.hayConflictoHorarios(nuevoCont.getFechaInicio(),nuevoCont.getFechaFinal())){
                throw new ConflictoHorariosException(conflictoHorariosMsg);
            }
            nuevoCont.setIdArrendatario(idArrendatario);
            nuevoCont.setIdPropiedad(idPropiedad);
            //ahora calculamos el precio
            float precioNoche = repositorioPropiedades.findPrecioById(idPropiedad).orElse(-1f);
            //si no hay precio, la propiedad es inválida o no existe
            if(precioNoche < 0)
            {
                throw new PropNoEncontradaException(propNoEncontradaMsg);
            }
            //si llega hasta aquí significa que la propiedad sí existe, por ende proseguimos (se hace un ceil en caso que sea de menos de 1 día, pues igual se le cobra lo del día, para que tenga mas sentido según la lógica del negocio)
            long cantiNoches = Duration.between(nuevoCont.getFechaInicio(), nuevoCont.getFechaFinal()).toDays();
            //si la diferencia es de menos de 1 día igual se cobra el día (no se puede dar arriendos de gratis)
            if(cantiNoches < 1)
                cantiNoches = 1;
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
