package com.paolicias.service_resumen.repository;

import com.paolicias.service_resumen.model.Resumen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResumenRepository extends JpaRepository<Resumen, Long> {

    @Query("SELECT r FROM Resumen r WHERE r.id_evento = :idEvento")
    List<Resumen> buscarPorEvento(@Param("idEvento") Long idEvento);
}