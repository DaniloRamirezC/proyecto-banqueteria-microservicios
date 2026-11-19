package com.paolicias.service_resumen.service;

import com.paolicias.service_resumen.model.Resumen;
import java.util.List;

public interface ResumenService {
    List<Resumen> listarResumenes();
    Resumen obtenerPorId(Long id);
    List<Resumen> listarPorEvento(Long idEvento);
    Resumen guardarResumen(Resumen resumen);
}
