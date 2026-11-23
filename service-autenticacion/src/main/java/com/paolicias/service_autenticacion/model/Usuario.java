package com.paolicias.service_autenticacion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor 
@NoArgsConstructor 
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;
    private String contrasena;
    @Column(unique = true, nullable = false)
    private String correo;
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}
