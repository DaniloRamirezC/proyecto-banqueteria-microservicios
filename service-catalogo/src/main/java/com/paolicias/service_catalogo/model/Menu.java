package com.paolicias.service_catalogo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_menu")
    private Long idMenu;

    @NotBlank
    @Column(name = "nombre_menu", nullable = false, length = 150)
    private String nombre;

    @Column(length = 600)
    private String detalle;

    @NotNull
    @PositiveOrZero
    @Column(precision = 12, scale = 2)
    private BigDecimal precio;

    @NotNull
    @Column(name = "id_categoria", nullable = false)
    private Long idCategoria;
}
