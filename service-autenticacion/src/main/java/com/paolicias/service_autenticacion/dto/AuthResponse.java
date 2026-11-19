package com.paolicias.service_autenticacion.dto;

public class AuthResponse {
    private String token;
    private Long idUsuario;
    private String nombreUsuario;
    private String correo;
    private String rol;

    public AuthResponse(String token, Long idUsuario, String nombreUsuario, String correo, String rol) {
        this.token = token;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.rol = rol;
    }

    public String getToken() { return token; }
    public Long getIdUsuario() { return idUsuario; }
    public String getNombreUsuario() { return nombreUsuario; }
    public String getCorreo() { return correo; }
    public String getRol() { return rol; }
}
