package com.paolicias.service_autenticacion.dto;

import lombok.Data;

@Data
public class RegistroRequest {
    private String nombreUsuario;
    private String correo;
    private String contrasena;
    private String nombreRol;
}
