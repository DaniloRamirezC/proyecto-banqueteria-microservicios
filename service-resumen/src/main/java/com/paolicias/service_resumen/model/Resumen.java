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
    private Integer precio_persona;

    @Transient
    private Integer canti_personas;

    @Column(nullable = false)
    private Integer subtotal;

    @Column(nullable = false)
    private Integer iva;

    @Column(nullable = false)
    private Integer total;

    @Column(nullable = false)
    private Integer monto_abono;

    @PrePersist
    @PreUpdate
    public void calcularMontos() {
        int precio = precio_persona != null ? precio_persona : 0;
        int cantidad = canti_personas != null ? canti_personas : 1;

        this.subtotal = (int) (precio * cantidad);
        this.iva = (int) (this.subtotal * 0.19);
        this.total = (int) (this.subtotal + this.iva);
        this.monto_abono = (int) (this.total /2);
    }
}