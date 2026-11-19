package com.paolicias.service_resumen.service;

import com.paolicias.service_resumen.model.Comprobante;
import com.paolicias.service_resumen.model.Resumen;
import com.paolicias.service_resumen.repository.ComprobanteRepository;
import com.paolicias.service_resumen.repository.ResumenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final ResumenRepository resumenRepository;

    public ComprobanteServiceImpl(ComprobanteRepository comprobanteRepository, ResumenRepository resumenRepository) {
        this.comprobanteRepository = comprobanteRepository;
        this.resumenRepository = resumenRepository;
    }

    @Override
    public List<Comprobante> listarComprobantes() {
        return comprobanteRepository.findAll();
    }

    @Override
    public Comprobante obtenerPorId(Long id) {
        return comprobanteRepository.findById(id).orElse(null);
    }

    @Override
    public Comprobante registrarComprobante(Comprobante comprobante) {
        if (comprobante.getResumen() != null && comprobante.getResumen().getIdResumen() != null) {
            Resumen resumen = resumenRepository.findById(comprobante.getResumen().getIdResumen()).orElse(null);
            if (resumen != null) {
                comprobante.setResumen(resumen);
                comprobante.setTotal(resumen.getTotal());
                comprobante.setAbono(resumen.getMonto_abono());
            }
        }
        return comprobanteRepository.save(comprobante);
    }

    @Override
    public Comprobante actualizarEstadoAbono(Long id, String estadoAbono) {
        Comprobante comprobante = comprobanteRepository.findById(id).orElse(null);
        if (comprobante != null) {
            comprobante.setEstadoAbono(estadoAbono);
            return comprobanteRepository.save(comprobante);
        }
        return null;
    }
}
