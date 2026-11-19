package com.paolicias.service_administrador.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table(name = "validacion_pago")
@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class ValidacionPago {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idValidacion;
    private Long idAdmin;
    private Long idComprobante;
    private Long idEvento;
    private Boolean estadoValidacion;
    private String observacionAdmin;
    private LocalDateTime fechaRevision;

    @PrePersist 
    public void prePersist(){
        if(this.fechaRevision == null){
            this.fechaRevision = LocalDateTime.now();
        }
    }
}
