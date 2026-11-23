package com.paolicias.servicio_pedido.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "detalle_evento")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class DetalleEvento {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleEvento;
    private String nombreItem;
    private Integer precioUnitario;
    private Long menuId; //Referencia lógica al microservicio service-catalogo

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evento_id", nullable = false)
    @JsonBackReference("evento-detalles")
    private Evento evento;
}
