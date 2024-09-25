package com.example.ProyectoWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDTO {
    private String correo;
    private String contraseña;
}
