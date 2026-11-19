package com.paolicias.service_administrador.service;

import java.util.List;
import java.util.Optional;

import com.paolicias.service_administrador.model.Administrador;

public interface AdminService {
    List<Administrador> listarTodos();
    Optional<Administrador> obtenerPorId(Long id);
    Optional<Administrador> obtenerPorUsuarioId(Long usuarioId);
    Administrador crear(Administrador administrador);
    Administrador actualizar(Long id, Administrador administrador);
    void eliminar(Long id);
}
