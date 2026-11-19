package com.paolicias.service_resumen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "comprobantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comprobante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_comprobante;

    @ManyToOne
    @JoinColumn(name = "id_resumen", nullable = false)
    private Resumen resumen;

    @Column(nullable = false)
    private Double total;

    @Column(nullable = false)
    private Double abono;

    @Column(nullable = false)
    private String estadoAbono; // "Pendiente" o "Pagado en 50%"

    private String url_comprobante;

    @Column(nullable = false)
    private Date fecha_registro;

    @PrePersist
    public void prePersist() {
        if (this.fecha_registro == null) {
            this.fecha_registro = new Date();
        }
        if (this.estadoAbono == null || this.estadoAbono.isBlank()) {
            this.estadoAbono = "Pendiente";
        }
        if (this.resumen != null) {
            if (this.total == null) {
                this.total = this.resumen.getTotal();
            }
            if (this.abono == null) {
                this.abono = this.resumen.getMonto_abono();
            }
        }
    }
}