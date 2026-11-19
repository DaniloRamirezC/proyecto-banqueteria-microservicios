package com.paolicias.servicio_pedido.service;

import java.util.List;
import java.util.Optional;

import com.paolicias.servicio_pedido.model.Evento;

public interface EventoService {
    List<Evento> listarTodos();
    Optional<Evento> obtenerPorId(Long id);
    List<Evento> obtenerPorCliente(Long clienteId);
    Evento crearEvento(Evento evento);
}
