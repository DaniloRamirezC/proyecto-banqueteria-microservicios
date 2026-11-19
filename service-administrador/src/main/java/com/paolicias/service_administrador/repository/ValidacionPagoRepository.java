package com.paolicias.service_administrador.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paolicias.service_administrador.model.ValidacionPago;

public interface ValidacionPagoRepository extends JpaRepository<ValidacionPago, Long> {
    List<ValidacionPago> findByIdAdminOrderByFechaRevisionDesc(Long idAdmin);
    List<ValidacionPago> findByIdEvento(Long idEvento);
}
