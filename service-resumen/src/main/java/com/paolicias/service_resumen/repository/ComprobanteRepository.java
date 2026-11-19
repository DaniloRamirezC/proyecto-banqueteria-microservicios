package com.paolicias.service_resumen.repository;

import com.paolicias.service_resumen.model.Comprobante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteRepository extends JpaRepository<Comprobante, Long> {
}
