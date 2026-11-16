package com.paolicias.service_administrador.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "administrador")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Administrador {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long idAdmin;
    @Column(name = "nombre_completo", nullable = false, length = 200)
    private String nombreCompleto;
    @Column(name = "cargo", nullable = false, length = 150)
    private String cargo;
    @Column(name = "usuario_id", nullable = false, unique = true)
    private Long usuarioId;
}
