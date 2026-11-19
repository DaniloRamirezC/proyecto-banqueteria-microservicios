package com.paolicias.service_resumen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resumenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Resumen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResumen;

    @Column(nullable = false)
    private Long id_evento;

    @Column(nullable = false)
    private Double precio_persona;

    @Transient
    private Integer canti_personas;

    @Column(nullable = false)
    private Double subtotal;

    @Column(nullable = false)
    private Double iva;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Double monto_abono;

    @PrePersist
    @PreUpdate
    public void calcularMontos() {
        double precio = precio_persona != null ? precio_persona : 0.0;
        int cantidad = canti_personas != null ? canti_personas : 1;

        this.subtotal = precio * cantidad;
        this.iva = this.subtotal * 0.19;
        this.total = this.subtotal + this.iva;
        this.monto_abono = this.total * 0.50;
    }
}