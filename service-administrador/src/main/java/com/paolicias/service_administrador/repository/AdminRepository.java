package com.paolicias.service_administrador.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paolicias.service_administrador.model.Administrador;

public interface AdminRepository extends JpaRepository<Administrador,Long>{
    Optional<Administrador> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioId(Long usuarioId);
}
