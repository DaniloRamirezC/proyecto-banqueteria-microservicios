package com.paolicias.service_administrador.service;

import java.util.List;
import java.util.Optional;

import com.paolicias.service_administrador.dto.EstimacionOperativaDTO;
import com.paolicias.service_administrador.model.Administrador;
import com.paolicias.service_administrador.model.ValidacionPago;

public interface AdminService {
    List<Administrador> listarTodos();
    Optional<Administrador> obtenerPorId(Long id);
    Optional<Administrador> obtenerPorUsuarioId(Long usuarioId);
    Administrador crear(Administrador administrador);
    Administrador actualizar(Long id, Administrador administrador);
    void eliminar(Long id);

    EstimacionOperativaDTO calcularEstimacion(int cantiAsistentes, String tipoEvento);
    ValidacionPago registraValidacionPago(Long idAdmin, ValidacionPago validacion);
    List<ValidacionPago> listarValidacionPorAdmin(Long idAdmin);
}
