package com.paolicias.servicio_pedido.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "evento")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class Evento {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEvento;
    private String tipoEvento;
    private LocalDate fecha;
    private String horario;
    private Integer cantiPersonas;
    private String observaciones;
    private Long clienteId; //Referencia lógica al microservicio cliente

    @JsonManagedReference("evento-detalles")
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleEvento> detalles = new ArrayList<>();

}
