package com.paolicias.servicio_pedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paolicias.servicio_pedido.model.Evento;
import com.paolicias.servicio_pedido.repository.EventoRepository;

@Service 
public class EventoServiceImpl implements EventoService {
    
    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository){
        this.eventoRepository = eventoRepository;
    }

    @Override 
    public List<Evento> listarTodos(){
        return eventoRepository.findAll();
    }

    @Override 
    public Optional<Evento> obtenerPorId(Long id){
        return eventoRepository.findById(id);
    }

    @Override 
    public List<Evento> obtenerPorCliente(Long clienteId){
        return eventoRepository.findByClienteId(clienteId);
    }

    @Override 
    public Evento crearEvento(Evento evento){
        if(evento.getDetalles() != null){
            evento.getDetalles().forEach(detalle -> detalle.setEvento(evento));
        }
        return eventoRepository.save(evento);
    }
}
