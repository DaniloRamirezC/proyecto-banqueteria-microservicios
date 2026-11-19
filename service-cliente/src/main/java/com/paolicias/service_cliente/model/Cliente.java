package com.paolicias.service_cliente.model;
import jakarta.persistence.*; import jakarta.validation.constraints.*; import lombok.*;
@Entity @Table(name="cliente") @Data @NoArgsConstructor @AllArgsConstructor
public class Cliente {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id_cliente") private Long idCliente;
 @NotBlank @Column(nullable=false,length=160) private String nombre;
 @NotBlank @Column(nullable=false,length=12,unique=true) private String rut;
 @Column(length=180) private String empresa;
 @NotBlank @Column(nullable=false,length=30) private String telefono;
 @NotBlank @Email @Column(nullable=false,length=180,unique=true) private String email;
}
