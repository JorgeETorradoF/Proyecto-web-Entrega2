package com.example.ProyectoWeb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginDTO {
    public LoginDTO() {
        //TODO Auto-generated constructor stub
    }
    private String correo;
    private String contraseña;
}
