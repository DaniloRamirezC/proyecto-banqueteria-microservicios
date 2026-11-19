package com.paolicias.service_administrador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor 
public class EstimacionOperativaDTO {
    private String tipoEvento;
    private int cantiAsistentes;
    private int garzonesRequeridos;
    private int ayudantesCocinaRequeridos;
    private int totalPersonal;
    private String tramoInsumos;
    private String detalleInsumos; 
}
