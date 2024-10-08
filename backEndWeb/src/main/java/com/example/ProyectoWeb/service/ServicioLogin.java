package com.example.ProyectoWeb.service;

import com.example.ProyectoWeb.dto.LoginDTO;
import com.example.ProyectoWeb.dto.RespuestaLoginDTO;
import com.example.ProyectoWeb.exception.CorreoNoExistenteException;
import com.example.ProyectoWeb.model.Arrendadores;
import com.example.ProyectoWeb.model.Arrendatarios;
import com.example.ProyectoWeb.repository.RepositorioArrendadores;
import com.example.ProyectoWeb.repository.RepositorioArrendatarios;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioLogin {

    @Autowired
    private RepositorioArrendadores repositorioArrendadores;

    @Autowired
    private RepositorioArrendatarios repositorioArrendatarios;

    public boolean contraseñaValida(String contraseña)
    {
        //luego validamos lo de contraseña segura con x digitos y mezclando simbolos y tal
        return !contraseña.isEmpty();
    }
    //validamos correo con regex
    public boolean emailValido(String email)
    {
        String EMAIL_PATTERN = 
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        if (email == null || email.isEmpty()) {
            return false;
        }
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public RespuestaLoginDTO loginUser(LoginDTO loginDTO) throws CorreoNoExistenteException {
        RespuestaLoginDTO respuesta = new RespuestaLoginDTO(0, false);
        //si todos los campos están llenos y son válidos se prosigue con el registro
        if( contraseñaValida(loginDTO.getContraseña()) && emailValido(loginDTO.getCorreo()))
        {

            //revisamos que el correo exista y donde
            boolean existeCorreoArrendador = repositorioArrendadores.existsByCorreo(loginDTO.getCorreo());
            boolean existeCorreoArrendatario = repositorioArrendatarios.existsByCorreo(loginDTO.getCorreo());

            //dependiendo de si hay correo registrado o no y que tipo de usuario será se realiza el registro
            if (existeCorreoArrendador) {
                Arrendadores arrendador = repositorioArrendadores.findByCorreo(loginDTO.getCorreo());
                if (arrendador.getContraseña().equals(loginDTO.getContraseña())){
                    respuesta.setId(arrendador.getId());
                    respuesta.setArrendador(true);
                }  
            } else if(existeCorreoArrendatario) {
                Arrendatarios arrendatario = repositorioArrendatarios.findByCorreo(loginDTO.getCorreo());
                if (arrendatario.getContraseña().equals(loginDTO.getContraseña())){
                    respuesta.setId(arrendatario.getId());
                    respuesta.setArrendador(false);
                }
            }
            else {
                //mensaje de error correo no existente
                throw new CorreoNoExistenteException("Correo no existente");
            }
        }
        return respuesta;
    }
}