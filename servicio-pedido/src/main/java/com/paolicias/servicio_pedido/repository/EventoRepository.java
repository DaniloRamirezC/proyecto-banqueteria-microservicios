package com.paolicias.servicio_pedido.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paolicias.servicio_pedido.model.Evento;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {
    List<Evento> findByClienteId(Long clienteId);
}
