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
    private Long id_usuario;
    private String nombre_usuario;
    private String contrasena;
    @Column(unique = true, nullable = false)
    private String correo;
    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol rol;
}
