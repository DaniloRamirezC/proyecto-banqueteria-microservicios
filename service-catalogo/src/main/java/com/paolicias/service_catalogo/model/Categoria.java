package com.paolicias.service_catalogo.model;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name="categoria") @Data @NoArgsConstructor @AllArgsConstructor
public class Categoria {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) @Column(name="id_categoria") private Long idCategoria;
 @Column(name="nombre_categoria",nullable=false,unique=true,length=80) private String nombreCategoria;
}
