package com.paolicias.service_resumen.service;

import com.paolicias.service_resumen.model.Comprobante;
import java.util.List;

public interface ComprobanteService {
    List<Comprobante> listarComprobantes();
    Comprobante obtenerPorId(Long id);
    Comprobante registrarComprobante(Comprobante comprobante);
    Comprobante actualizarEstadoAbono(Long id, String estadoAbono);
}