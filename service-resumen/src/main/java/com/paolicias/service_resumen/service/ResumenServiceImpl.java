package com.paolicias.service_resumen.service;

import com.paolicias.service_resumen.model.Resumen;
import com.paolicias.service_resumen.repository.ResumenRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResumenServiceImpl implements ResumenService {

    private final ResumenRepository resumenRepository;

    public ResumenServiceImpl(ResumenRepository resumenRepository) {
        this.resumenRepository = resumenRepository;
    }

    @Override
    public List<Resumen> listarResumenes() {
        return resumenRepository.findAll();
    }

    @Override
    public Resumen obtenerPorId(Long id) {
        return resumenRepository.findById(id).orElse(null);
    }

    @Override
    public List<Resumen> listarPorEvento(Long idEvento) {
        return resumenRepository.buscarPorEvento(idEvento);
    }

    @Override
    public Resumen guardarResumen(Resumen resumen) {
        resumen.calcularMontos();
        return resumenRepository.save(resumen);
    }
}
