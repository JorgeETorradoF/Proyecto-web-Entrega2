package com.example.ProyectoWeb.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProyectoWeb.dto.ContratoDTO;
import com.example.ProyectoWeb.exception.CamposInvalidosException;
import com.example.ProyectoWeb.model.Contratos;
import com.example.ProyectoWeb.repository.RepositorioContratos;

@Service
public class ServicioContratos {

    private static final String mensajeCamposInvalidos = "No se admiten campos vacíos, intente de nuevo";

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RepositorioContratos repositorioContratos;

    //Aquí se comprueba que no haya campos vacíos
    public boolean checkCamposContrato(ContratoDTO contrato) 
    {
        if (contrato == null) {
            return false; //caso donde no tenga absolutamente nada
        }
    
        // Validar que el precio sea positivo
        if (contrato.getPrecio() <= 0) {
            return false;
        }

        // Validar que la fecha de inicio sea anterior a la fecha final
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

    public Contratos saveContrato(ContratoDTO contratoDTO, int idArrendatario, int idPropiedad) throws CamposInvalidosException
    {
        if(checkCamposContrato(contratoDTO))
        {
            Contratos nuevoCont = modelMapper.map(contratoDTO, Contratos.class);
            nuevoCont.setIdArrendatario(idArrendatario);
            nuevoCont.setIdPropiedad(idPropiedad);
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
